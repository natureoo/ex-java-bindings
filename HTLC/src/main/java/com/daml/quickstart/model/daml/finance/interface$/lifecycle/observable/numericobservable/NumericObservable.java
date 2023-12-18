package com.daml.quickstart.model.daml.finance.interface$.lifecycle.observable.numericobservable;

import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.Contract;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public final class NumericObservable {
  public static final Identifier TEMPLATE_ID = new Identifier("20176559c50772de2d90e9779dcfad78bc0edf2a949454c8cb78dde8cb89f780", "Daml.Finance.Interface.Lifecycle.Observable.NumericObservable", "NumericObservable");

  public static final Choice<NumericObservable, GetView, View> CHOICE_GetView = 
      Choice.create("GetView", value$ -> value$.toValue(), value$ -> GetView.valueDecoder()
        .decode(value$), value$ -> View.valueDecoder().decode(value$));

  public static final Choice<NumericObservable, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final Choice<NumericObservable, Observe, BigDecimal> CHOICE_Observe = 
      Choice.create("Observe", value$ -> value$.toValue(), value$ -> Observe.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromNumeric.decode(value$));

  public static final INTERFACE_ INTERFACE = new INTERFACE_();

  private NumericObservable() {
  }

  public static ContractFilter<Contract<ContractId, View>> contractFilter() {
    return ContractFilter.of(INTERFACE);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<NumericObservable> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, NumericObservable, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public interface Exercises<Cmd> extends com.daml.ledger.javaapi.data.codegen.Exercises<Cmd> {
    default Update<Exercised<View>> exerciseGetView(GetView arg) {
      return makeExerciseCmd(CHOICE_GetView, arg);
    }

    default Update<Exercised<View>> exerciseGetView(String viewer) {
      return exerciseGetView(new GetView(viewer));
    }

    default Update<Exercised<Unit>> exerciseArchive(Archive arg) {
      return makeExerciseCmd(CHOICE_Archive, arg);
    }

    default Update<Exercised<BigDecimal>> exerciseObserve(Observe arg) {
      return makeExerciseCmd(CHOICE_Observe, arg);
    }

    default Update<Exercised<BigDecimal>> exerciseObserve(Set<String> actors, Instant t) {
      return exerciseObserve(new Observe(actors, t));
    }
  }

  public static final class CreateAnd extends com.daml.ledger.javaapi.data.codegen.CreateAnd.ToInterface implements Exercises<CreateAndExerciseCommand> {
    public CreateAnd(ContractCompanion<?, ?, ?> companion, Template createArguments) {
      super(companion, createArguments);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, NumericObservable, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class ByKey extends com.daml.ledger.javaapi.data.codegen.ByKey.ToInterface implements Exercises<ExerciseByKeyCommand> {
    public ByKey(ContractCompanion<?, ?, ?> companion, Value key) {
      super(companion, key);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, NumericObservable, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class INTERFACE_ extends InterfaceCompanion<NumericObservable, ContractId, View> {
    INTERFACE_() {
      super(
            "com.daml.quickstart.model.daml.finance.interface$.lifecycle.observable.numericobservable.NumericObservable", NumericObservable.TEMPLATE_ID, ContractId::new, View.valueDecoder(), List.of(CHOICE_GetView,
            CHOICE_Archive, CHOICE_Observe));
    }
  }
}
