package com.daml.quickstart.model.daml.finance.interface$.settlement.factory;

import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.Contract;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.da.types.Tuple2;
import com.daml.quickstart.model.daml.finance.interface$.settlement.batch.Batch;
import com.daml.quickstart.model.daml.finance.interface$.settlement.instruction.Instruction;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.RoutedStep;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Id;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public final class Factory {
  public static final Identifier TEMPLATE_ID = new Identifier("4b5bf7a6465e5e11800a03c131a5759565edd3ff9cfc43f41d1f7fbc72255587", "Daml.Finance.Interface.Settlement.Factory", "Factory");

  public static final Choice<Factory, GetView, View> CHOICE_GetView = 
      Choice.create("GetView", value$ -> value$.toValue(), value$ -> GetView.valueDecoder()
        .decode(value$), value$ -> View.valueDecoder().decode(value$));

  public static final Choice<Factory, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final Choice<Factory, Instruct, Tuple2<Batch.ContractId, List<Instruction.ContractId>>> CHOICE_Instruct = 
      Choice.create("Instruct", value$ -> value$.toValue(), value$ -> Instruct.valueDecoder()
        .decode(value$), value$ ->
        Tuple2.<Batch.ContractId,
        List<Instruction.ContractId>>valueDecoder(v$0 ->
          new Batch.ContractId(v$0.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()),
        PrimitiveValueDecoders.fromList(v$1 ->
            new Instruction.ContractId(v$1.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue())))
        .decode(value$));

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
    default Update<Exercised<View>> exerciseGetView(GetView arg) {
      return makeExerciseCmd(CHOICE_GetView, arg);
    }

    default Update<Exercised<View>> exerciseGetView(String viewer) {
      return exerciseGetView(new GetView(viewer));
    }

    default Update<Exercised<Unit>> exerciseArchive(Archive arg) {
      return makeExerciseCmd(CHOICE_Archive, arg);
    }

    default Update<Exercised<Tuple2<Batch.ContractId, List<Instruction.ContractId>>>> exerciseInstruct(
        Instruct arg) {
      return makeExerciseCmd(CHOICE_Instruct, arg);
    }

    default Update<Exercised<Tuple2<Batch.ContractId, List<Instruction.ContractId>>>> exerciseInstruct(
        Set<String> instructors, Set<String> settlers, Id id, String description,
        Optional<Id> contextId, List<RoutedStep> routedSteps, Optional<Instant> settlementTime) {
      return exerciseInstruct(new Instruct(instructors, settlers, id, description, contextId,
          routedSteps, settlementTime));
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
            "com.daml.quickstart.model.daml.finance.interface$.settlement.factory.Factory", Factory.TEMPLATE_ID, ContractId::new, View.valueDecoder(), List.of(CHOICE_GetView,
            CHOICE_Archive, CHOICE_Instruct));
    }
  }
}
