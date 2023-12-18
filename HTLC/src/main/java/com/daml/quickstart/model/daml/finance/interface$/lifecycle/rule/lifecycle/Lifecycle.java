package com.daml.quickstart.model.daml.finance.interface$.lifecycle.rule.lifecycle;

import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.Contract;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.types.Tuple2;
import com.daml.quickstart.model.daml.finance.interface$.lifecycle.effect.Effect;
import com.daml.quickstart.model.daml.finance.interface$.lifecycle.event.Event;
import com.daml.quickstart.model.daml.finance.interface$.lifecycle.observable.numericobservable.NumericObservable;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.InstrumentKey;

import java.util.List;
import java.util.Optional;

public final class Lifecycle {
  public static final Identifier TEMPLATE_ID = new Identifier("20176559c50772de2d90e9779dcfad78bc0edf2a949454c8cb78dde8cb89f780", "Daml.Finance.Interface.Lifecycle.Rule.Lifecycle", "Lifecycle");

  public static final Choice<Lifecycle, Evolve, Tuple2<Optional<InstrumentKey>, List<Effect.ContractId>>> CHOICE_Evolve = 
      Choice.create("Evolve", value$ -> value$.toValue(), value$ -> Evolve.valueDecoder()
        .decode(value$), value$ ->
        Tuple2.<Optional<InstrumentKey>,
        List<Effect.ContractId>>valueDecoder(PrimitiveValueDecoders.fromOptional(
          InstrumentKey.valueDecoder()), PrimitiveValueDecoders.fromList(v$0 ->
            new Effect.ContractId(v$0.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue())))
        .decode(value$));

  public static final Choice<Lifecycle, GetView, View> CHOICE_GetView = 
      Choice.create("GetView", value$ -> value$.toValue(), value$ -> GetView.valueDecoder()
        .decode(value$), value$ -> View.valueDecoder().decode(value$));

  public static final Choice<Lifecycle, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final INTERFACE_ INTERFACE = new INTERFACE_();

  private Lifecycle() {
  }

  public static ContractFilter<Contract<ContractId, View>> contractFilter() {
    return ContractFilter.of(INTERFACE);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Lifecycle> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Lifecycle, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public interface Exercises<Cmd> extends com.daml.ledger.javaapi.data.codegen.Exercises<Cmd> {
    default Update<Exercised<Tuple2<Optional<InstrumentKey>, List<Effect.ContractId>>>> exerciseEvolve(
        Evolve arg) {
      return makeExerciseCmd(CHOICE_Evolve, arg);
    }

    default Update<Exercised<Tuple2<Optional<InstrumentKey>, List<Effect.ContractId>>>> exerciseEvolve(
        Event.ContractId eventCid, InstrumentKey instrument,
        List<NumericObservable.ContractId> observableCids) {
      return exerciseEvolve(new Evolve(eventCid, instrument, observableCids));
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
  }

  public static final class CreateAnd extends com.daml.ledger.javaapi.data.codegen.CreateAnd.ToInterface implements Exercises<CreateAndExerciseCommand> {
    public CreateAnd(ContractCompanion<?, ?, ?> companion, Template createArguments) {
      super(companion, createArguments);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Lifecycle, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class ByKey extends com.daml.ledger.javaapi.data.codegen.ByKey.ToInterface implements Exercises<ExerciseByKeyCommand> {
    public ByKey(ContractCompanion<?, ?, ?> companion, Value key) {
      super(companion, key);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Lifecycle, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class INTERFACE_ extends InterfaceCompanion<Lifecycle, ContractId, View> {
    INTERFACE_() {
      super(
            "com.daml.quickstart.model.daml.finance.interface$.lifecycle.rule.lifecycle.Lifecycle", Lifecycle.TEMPLATE_ID, ContractId::new, View.valueDecoder(), List.of(CHOICE_Evolve,
            CHOICE_GetView, CHOICE_Archive));
    }
  }
}
