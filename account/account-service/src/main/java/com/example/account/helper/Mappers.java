package com.example.account.helper;

import com.example.account.api.account.AccountInfoResult;
import com.example.account.api.account.GetAccountInfoDetailResult;
import com.example.account.api.person.GetPersonDetailResult;
import com.example.account.api.person.PersonResult;
import com.example.account.api.transaction.TransactionResult;
import com.example.account.api.transaction.TransactionType;
import com.example.account.api.transaction.TransferType;
import com.example.account.domain.AccountInfo;
import com.example.account.domain.Person;
import com.example.account.domain.Transaction;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class Mappers {
    public GetPersonDetailResult personsMapper(Person person) {
        GetPersonDetailResult result = new GetPersonDetailResult();
        result = this.personMapper(person, result);

        return result;
    }

    public GetAccountInfoDetailResult accountInfosMapper(AccountInfo accountInfo) {
        GetAccountInfoDetailResult result = new GetAccountInfoDetailResult();
        result = this.accountInfoMapper(accountInfo, result);

        return result;
    }

    public TransactionResult transactionsMapper(Transaction transaction) {
        TransactionResult result = new TransactionResult();
        result = this.transactionMapper(transaction, result);

        return result;
    }

    public <T extends Person, S extends PersonResult> S personMapper(T t, S s) {
        Function<T, S> function = c -> {
            s.setId(t.getId());
            s.setPersonName(t.getPersonName());
            s.setNationalCode(t.getNationalCode());
            s.setPhoneNumber(t.getPhoneNumber());
            if (t.getAccountInfo() != null) {
                AccountInfoResult result = new AccountInfoResult();
                s.setAccountInfo(accountInfoMapper(t.getAccountInfo(), result));
            }
            return s;
        };

        return function.apply(t);
    }

    public <A extends AccountInfo, P extends AccountInfoResult> P accountInfoMapper(A a, P p) {
        Function<A, P> function = c -> {
            p.setId(a.getId());
            p.setAccountNumber(a.getAccountNumber());
            p.setAmount(a.getAmount());
            p.setBalance(a.getBalance());
            p.setTransferType(TransferType.valueOfCode(a.getTransferTypeCode()));

            return p;
        };

        return function.apply(a);
    }

    public <K extends Transaction, M extends TransactionResult> M transactionMapper(K k, M m) {
        Function<K, M> function = c -> {
            m.setId(k.getId());
            m.setAmount(k.getAmount());
            m.setSrcBalance(k.getSrcBalance());
            m.setDestBalance(k.getDestBalance());
            m.setSourceAccountNumber(k.getSourceAccountNumber());
            m.setDestinationAccountNumber(k.getDestinationAccountNumber());
            m.setTransferDate(k.getTransferDate());
            m.setTrackingCode(k.getTrackingCode());
            m.setTransactionType(TransactionType.valueOfCode(k.getTransactionTypeCode()));
            if (k.getSrcTransferTypeCode() != null) {
                m.setSrcTransferType(TransferType.valueOfCode(k.getSrcTransferTypeCode()));
            }

            return m;
        };

        return function.apply(k);
    }

}
