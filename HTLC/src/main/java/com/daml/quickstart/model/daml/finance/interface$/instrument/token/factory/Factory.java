package com.daml.quickstart.model.daml.finance.interface$.instrument.token.factory;

import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.Contract;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.instrument.base.instrument.Instrument;
import com.daml.quickstart.model.daml.finance.interface$.instrument.token.types.Token;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.InstrumentKey;

import java.util.List;
import java.util.Map;

public final class Factory {
  public static final Identifier TEMPLATE_ID = new Identifier("30e810720119f9c9790bfcf623aab3f1fb9d3206b7c19ba26485a67f14b12c0c", "Daml.Finance.Interface.Instrument.Token.Factory", "Factory");

  public static final Choice<Factory, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final Choice<Factory, Create, Instrument.ContractId> CHOICE_Create = 
      Choice.create("Create", value$ -> value$.toValue(), value$ -> Create.valueDecoder()
        .decode(value$), value$ ->
        new Instrument.ContractId(value$.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()));

  public static final Choice<Factory, Remove, Unit> CHOICE_Remove = 
      Choice.create("Remove", value$ -> value$.toValue(), value$ -> Remove.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final INTERFACE_ INTERFACE = new INTERFACE_();

  private Factory() {
  }

  public static ContractFilter<Contract<ContractId, View>> contractFilter() {
    return ContractFilter.of(INTERFACE);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Factory> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Factory, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public interface Exercises<Cmd> extends com.daml.ledger.javaapi.data.codegen.Exercises<Cmd> {
    default Update<Exercised<Unit>> exerciseArchive(Archive arg) {
      return makeExerciseCmd(CHOICE_Archive, arg);
    }

    default Update<Exercised<Instrument.ContractId>> exerciseCreate(Create arg) {
      return makeExerciseCmd(CHOICE_Create, arg);
    }

    default Update<Exercised<Instrument.ContractId>> exerciseCreate(Token token,
        Map<String, Set<String>> observers) {
      return exerciseCreate(new Create(token, observers));
    }

    default Update<Exercised<Unit>> exerciseRemove(Remove arg) {
      return makeExerciseCmd(CHOICE_Remove, arg);
    }

    default Update<Exercised<Unit>> exerciseRemove(InstrumentKey instrument) {
      return exerciseRemove(new Remove(instrument));
    }
  }

  public static final class CreateAnd extends com.daml.ledger.javaapi.data.codegen.CreateAnd.ToInterface implements Exercises<CreateAndExerciseCommand> {
    public CreateAnd(ContractCompanion<?, ?, ?> companion, Template createArguments) {
      super(companion, createArguments);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Factory, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class ByKey extends com.daml.ledger.javaapi.data.codegen.ByKey.ToInterface implements Exercises<ExerciseByKeyCommand> {
    public ByKey(ContractCompanion<?, ?, ?> companion, Value key) {
      super(companion, key);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Factory, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class INTERFACE_ extends InterfaceCompanion<Factory, ContractId, View> {
    INTERFACE_() {
      super(
            "com.daml.quickstart.model.daml.finance.interface$.instrument.token.factory.Factory", Factory.TEMPLATE_ID, ContractId::new, View.valueDecoder(), List.of(CHOICE_Archive,
            CHOICE_Create, CHOICE_Remove));
    }
  }
}
