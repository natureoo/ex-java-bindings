package com.daml.quickstart.model.daml.finance.interface$.settlement.batch;

import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.Contract;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.holding.base.Base;

import java.util.List;

public final class Batch {
  public static final Identifier TEMPLATE_ID = new Identifier("4b5bf7a6465e5e11800a03c131a5759565edd3ff9cfc43f41d1f7fbc72255587", "Daml.Finance.Interface.Settlement.Batch", "Batch");

  public static final Choice<Batch, GetView, View> CHOICE_GetView = 
      Choice.create("GetView", value$ -> value$.toValue(), value$ -> GetView.valueDecoder()
        .decode(value$), value$ -> View.valueDecoder().decode(value$));

  public static final Choice<Batch, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final Choice<Batch, Settle, List<Base.ContractId>> CHOICE_Settle = 
      Choice.create("Settle", value$ -> value$.toValue(), value$ -> Settle.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromList(v$0 ->
            new Base.ContractId(v$0.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()))
        .decode(value$));

  public static final Choice<Batch, Cancel, List<Base.ContractId>> CHOICE_Cancel = 
      Choice.create("Cancel", value$ -> value$.toValue(), value$ -> Cancel.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromList(v$0 ->
            new Base.ContractId(v$0.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()))
        .decode(value$));

  public static final INTERFACE_ INTERFACE = new INTERFACE_();

  private Batch() {
  }

  public static ContractFilter<Contract<ContractId, View>> contractFilter() {
    return ContractFilter.of(INTERFACE);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Batch> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Batch, ?> getCompanion(
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

    default Update<Exercised<List<Base.ContractId>>> exerciseSettle(Settle arg) {
      return makeExerciseCmd(CHOICE_Settle, arg);
    }

    default Update<Exercised<List<Base.ContractId>>> exerciseSettle(Set<String> actors) {
      return exerciseSettle(new Settle(actors));
    }

    default Update<Exercised<List<Base.ContractId>>> exerciseCancel(Cancel arg) {
      return makeExerciseCmd(CHOICE_Cancel, arg);
    }

    default Update<Exercised<List<Base.ContractId>>> exerciseCancel(Set<String> actors) {
      return exerciseCancel(new Cancel(actors));
    }
  }

  public static final class CreateAnd extends com.daml.ledger.javaapi.data.codegen.CreateAnd.ToInterface implements Exercises<CreateAndExerciseCommand> {
    public CreateAnd(ContractCompanion<?, ?, ?> companion, Template createArguments) {
      super(companion, createArguments);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Batch, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class ByKey extends com.daml.ledger.javaapi.data.codegen.ByKey.ToInterface implements Exercises<ExerciseByKeyCommand> {
    public ByKey(ContractCompanion<?, ?, ?> companion, Value key) {
      super(companion, key);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Batch, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class INTERFACE_ extends InterfaceCompanion<Batch, ContractId, View> {
    INTERFACE_() {
      super(
            "com.daml.quickstart.model.daml.finance.interface$.settlement.batch.Batch", Batch.TEMPLATE_ID, ContractId::new, View.valueDecoder(), List.of(CHOICE_GetView,
            CHOICE_Archive, CHOICE_Settle, CHOICE_Cancel));
    }
  }
}
