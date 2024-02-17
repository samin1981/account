package com.example.account.service;

import com.example.account.api.person.*;
import com.example.account.builder.PersonBuilder;
import com.example.account.comon.AccountErrorsStatic;
import com.example.account.comon.AccountException;
import com.example.account.domain.Person;
import com.example.account.helper.AccountMapper;
import com.example.account.repository.PersonRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.twilio.example.Example.ACCOUNT_SID;
import static com.twilio.example.Example.AUTH_TOKEN;

@Service
@Transactional(rollbackFor = Throwable.class)
public class PersonService {
    private static final Logger logger = LogManager.getLogger(PersonService.class);
    @Autowired
    private final PersonRepository personRepository;
    private final AccountMapper accountMapper;

    public PersonService(PersonRepository personRepository,
                         AccountMapper accountMapper) {
        this.personRepository = personRepository;
        this.accountMapper = accountMapper;
    }

    public GetAllPersonsResult getAllPersons() {
        GetAllPersonsResult result = new GetAllPersonsResult();

        List<com.example.account.api.person.GetPersonDetailResult> persons = personRepository.findAllByDeleted().stream()
                .map(person -> accountMapper.personsMapperForPersonDetail(person)).collect(Collectors.toList());
        if (persons.isEmpty() && persons.size() == 0) {
            logger.error("Person not found");
            throw new AccountException(AccountErrorsStatic.ERROR_PERSON_NOT_FOUND, null);
        }
        result.setItems(persons);
        return result;
    }

    public GetPersonByNationalCodeResult getPersonByNationalCode(GetPersonByNationalCodeRequest request) {
        Person existPerson = personRepository.findPersonByNationalCode(request.getNationalCode())
                .orElseThrow(() -> new AccountException(AccountErrorsStatic.ERROR_PERSON_NOT_FOUND, request.getNationalCode()));

        return accountMapper.personsMapperForPersonByNationalCode(existPerson);
    }

    public void addPerson(AddPersonRequest request) {
        Optional person = personRepository.findPersonByNationalCode(request.getNationalCode());
        if (person.isPresent()) {
            logger.error("Person with national code {} exists.", request.getNationalCode());
            throw new AccountException(AccountErrorsStatic.ERROR_PERSON_EXIST, request.getNationalCode());
        }

        Person newPerson = PersonBuilder.getInstance()
                .personName(request.getPersonName())
                .phoneNumber(request.getPhoneNumber())
                .nationalCode(request.getNationalCode())
                .deleted(0)
                .accountInfo(null)
                .build();

        personRepository.save(newPerson);
        logger.info("New person with national code {} created", request.getNationalCode());
    }

    public void removePersonByNationalCode(RemovePersonByNationalCodeRequest request) {
        Person existPerson = personRepository.findPersonByNationalCode(request.getNationalCode())
                .orElseThrow(() -> new AccountException(AccountErrorsStatic.ERROR_PERSON_NOT_FOUND, request.getNationalCode()));

        personRepository.removePersonById(existPerson.getId());
        personRepository.save(existPerson);
        logger.info("person {0} was deleted", existPerson.getNationalCode());
    }

    public GetPersonByAccountNumberResult getPersonByAccountNumber(GetPersonByAccountNumberRequest request) {
        Person person = personRepository.findPersonByAccountInfo(request.getAccountNumber());
        if (person == null) {
            logger.info("Person with account number {} not found", request.getAccountNumber());
            throw new AccountException(AccountErrorsStatic.ERROR_PERSON_WITH_ACCOUNT_NUMBER_NOT_FOUND, request.getAccountNumber());
        }

        return accountMapper.personMapperForPersonByAccountNumber(person);
    }

    public void getDebtors(Date date) {
        List<Person> persons = personRepository.getDebtors(date);
        sendSms(persons);
    }

    private void sendSms(List<Person> persons) {
        persons.stream().map(c -> {
//            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Twilio.init("US24f5a5aaf1f06fad5bc7668abc79f229", "LZQFGWKPCCWN72EZXB1DSJLS");

            com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message
                    .creator(
                            new PhoneNumber("+12225559999"),
                            new PhoneNumber("+989917142283"),
                            "Sample Twilio SMS using Java")
                    .create();
            message.getBody();

            return null;
        }).collect(Collectors.toList());
    }

    public void getJasperReportForPersons(HttpServletResponse response) throws JRException, IOException {

        List<Person> persons = personRepository.findAllByDeleted();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("firstParam", "این یک متن جهت تست است");

        String filePath = "C:\\Myproject\\account\\account-service\\src\\main\\resources\\personReport.jrxml";
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(persons);

        JasperReport report = JasperCompileManager.compileReport(filePath);
        JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

        //create pdf file
        JasperExportManager.exportReportToPdfFile(print, "D:\\files\\personReport.pdf");
        logger.info("Report Created.");

        //create download file in swagger
        JRXlsxExporter exporter = new JRXlsxExporter();
        SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
        reportConfigXLS.setSheetNames(new String[]{"person report title"});
        reportConfigXLS.setDetectCellType(true);
        reportConfigXLS.setCollapseRowSpan(false);
        exporter.setConfiguration(reportConfigXLS);
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        response.setHeader(
                HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "generatedFile" + ".xlsx;");
        response.setContentType("application/octet-stream");
        exporter.exportReport();
        logger.info("Report Created.");


    }

}
