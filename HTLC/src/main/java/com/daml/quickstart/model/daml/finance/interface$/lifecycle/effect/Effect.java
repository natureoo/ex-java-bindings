package com.daml.quickstart.model.daml.finance.interface$.lifecycle.effect;

import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.Contract;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.holding.base.Base;

import java.util.List;

public final class Effect {
  public static final Identifier TEMPLATE_ID = new Identifier("20176559c50772de2d90e9779dcfad78bc0edf2a949454c8cb78dde8cb89f780", "Daml.Finance.Interface.Lifecycle.Effect", "Effect");

  public static final Choice<Effect, Calculate, CalculationResult> CHOICE_Calculate = 
      Choice.create("Calculate", value$ -> value$.toValue(), value$ -> Calculate.valueDecoder()
        .decode(value$), value$ -> CalculationResult.valueDecoder().decode(value$));

  public static final Choice<Effect, GetView, View> CHOICE_GetView = 
      Choice.create("GetView", value$ -> value$.toValue(), value$ -> GetView.valueDecoder()
        .decode(value$), value$ -> View.valueDecoder().decode(value$));

  public static final Choice<Effect, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final Choice<Effect, SetProviders, ContractId> CHOICE_SetProviders = 
      Choice.create("SetProviders", value$ -> value$.toValue(), value$ ->
        SetProviders.valueDecoder().decode(value$), value$ ->
        new ContractId(value$.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()));

  public static final INTERFACE_ INTERFACE = new INTERFACE_();

  private Effect() {
  }

  public static ContractFilter<Contract<ContractId, View>> contractFilter() {
    return ContractFilter.of(INTERFACE);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Effect> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Effect, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public interface Exercises<Cmd> extends com.daml.ledger.javaapi.data.codegen.Exercises<Cmd> {
    default Update<Exercised<CalculationResult>> exerciseCalculate(Calculate arg) {
      return makeExerciseCmd(CHOICE_Calculate, arg);
    }

    default Update<Exercised<CalculationResult>> exerciseCalculate(String actor,
        Base.ContractId holdingCid) {
      return exerciseCalculate(new Calculate(actor, holdingCid));
    }

    default Update<Exercised<View>> exerciseGetView(GetView arg) {
      return makeExerciseCmd(CHOICE_GetView, arg);
    }

    default Update<Exercised<View>> exerciseGetView(String viewer) {
      return exerciseGetView(new GetView(viewer));
    }

    default Update<Exercised<Unit>> exerciseArchive(Archive arg) {
      return makeExerciseCmd(CHOICE_Archive, arg);
    }

    default Update<Exercised<ContractId>> exerciseSetProviders(SetProviders arg) {
      return makeExerciseCmd(CHOICE_SetProviders, arg);
    }

    default Update<Exercised<ContractId>> exerciseSetProviders(Set<String> newProviders) {
      return exerciseSetProviders(new SetProviders(newProviders));
    }
  }

  public static final class CreateAnd extends com.daml.ledger.javaapi.data.codegen.CreateAnd.ToInterface implements Exercises<CreateAndExerciseCommand> {
    public CreateAnd(ContractCompanion<?, ?, ?> companion, Template createArguments) {
      super(companion, createArguments);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Effect, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class ByKey extends com.daml.ledger.javaapi.data.codegen.ByKey.ToInterface implements Exercises<ExerciseByKeyCommand> {
    public ByKey(ContractCompanion<?, ?, ?> companion, Value key) {
      super(companion, key);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Effect, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class INTERFACE_ extends InterfaceCompanion<Effect, ContractId, View> {
    INTERFACE_() {
      super(
            "com.daml.quickstart.model.daml.finance.interface$.lifecycle.effect.Effect", Effect.TEMPLATE_ID, ContractId::new, View.valueDecoder(), List.of(CHOICE_Calculate,
            CHOICE_GetView, CHOICE_Archive, CHOICE_SetProviders));
    }
  }
}
