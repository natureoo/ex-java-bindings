package com.daml.quickstart.model.daml.finance.interface$.account.account;

import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.Contract;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.daml.finance.interface$.holding.base.Base;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.InstrumentKey;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Quantity;

import java.math.BigDecimal;
import java.util.List;

public final class Account {
  public static final Identifier TEMPLATE_ID = new Identifier("615590cf0b4e5ea01cb08ef577e43d6cfcd816be060ac7a540a100c3f00b11b2", "Daml.Finance.Interface.Account.Account", "Account");

  public static final Choice<Account, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final Choice<Account, GetView, View> CHOICE_GetView = 
      Choice.create("GetView", value$ -> value$.toValue(), value$ -> GetView.valueDecoder()
        .decode(value$), value$ -> View.valueDecoder().decode(value$));

  public static final Choice<Account, Credit, Base.ContractId> CHOICE_Credit = 
      Choice.create("Credit", value$ -> value$.toValue(), value$ -> Credit.valueDecoder()
        .decode(value$), value$ ->
        new Base.ContractId(value$.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()));

  public static final Choice<Account, Debit, Unit> CHOICE_Debit = 
      Choice.create("Debit", value$ -> value$.toValue(), value$ -> Debit.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final INTERFACE_ INTERFACE = new INTERFACE_();

  private Account() {
  }

  public static ContractFilter<Contract<ContractId, View>> contractFilter() {
    return ContractFilter.of(INTERFACE);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Account> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Account, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public interface Exercises<Cmd> extends com.daml.ledger.javaapi.data.codegen.Exercises<Cmd> {
    default Update<Exercised<Unit>> exerciseArchive(Archive arg) {
      return makeExerciseCmd(CHOICE_Archive, arg);
    }

    default Update<Exercised<View>> exerciseGetView(GetView arg) {
      return makeExerciseCmd(CHOICE_GetView, arg);
    }

    default Update<Exercised<View>> exerciseGetView(String viewer) {
      return exerciseGetView(new GetView(viewer));
    }

    default Update<Exercised<Base.ContractId>> exerciseCredit(Credit arg) {
      return makeExerciseCmd(CHOICE_Credit, arg);
    }

    default Update<Exercised<Base.ContractId>> exerciseCredit(
        Quantity<InstrumentKey, BigDecimal> quantity) {
      return exerciseCredit(new Credit(quantity));
    }

    default Update<Exercised<Unit>> exerciseDebit(Debit arg) {
      return makeExerciseCmd(CHOICE_Debit, arg);
    }

    default Update<Exercised<Unit>> exerciseDebit(Base.ContractId holdingCid) {
      return exerciseDebit(new Debit(holdingCid));
    }
  }

  public static final class CreateAnd extends com.daml.ledger.javaapi.data.codegen.CreateAnd.ToInterface implements Exercises<CreateAndExerciseCommand> {
    public CreateAnd(ContractCompanion<?, ?, ?> companion, Template createArguments) {
      super(companion, createArguments);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Account, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class ByKey extends com.daml.ledger.javaapi.data.codegen.ByKey.ToInterface implements Exercises<ExerciseByKeyCommand> {
    public ByKey(ContractCompanion<?, ?, ?> companion, Value key) {
      super(companion, key);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Account, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class INTERFACE_ extends InterfaceCompanion<Account, ContractId, View> {
    INTERFACE_() {
      super(
            "com.daml.quickstart.model.daml.finance.interface$.account.account.Account", Account.TEMPLATE_ID, ContractId::new, View.valueDecoder(), List.of(CHOICE_Archive,
            CHOICE_GetView, CHOICE_Credit, CHOICE_Debit));
    }
  }
}
