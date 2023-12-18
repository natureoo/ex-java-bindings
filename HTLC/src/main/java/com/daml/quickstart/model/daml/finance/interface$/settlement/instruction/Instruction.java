package com.daml.quickstart.model.daml.finance.interface$.settlement.instruction;

import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.Contract;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.da.types.Tuple2;
import com.daml.quickstart.model.daml.finance.interface$.holding.base.Base;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.Allocation;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.Approval;

import java.util.List;
import java.util.Optional;

public final class Instruction {
  public static final Identifier TEMPLATE_ID = new Identifier("4b5bf7a6465e5e11800a03c131a5759565edd3ff9cfc43f41d1f7fbc72255587", "Daml.Finance.Interface.Settlement.Instruction", "Instruction");

  public static final Choice<Instruction, GetView, View> CHOICE_GetView = 
      Choice.create("GetView", value$ -> value$.toValue(), value$ -> GetView.valueDecoder()
        .decode(value$), value$ -> View.valueDecoder().decode(value$));

  public static final Choice<Instruction, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final Choice<Instruction, Approve, ContractId> CHOICE_Approve = 
      Choice.create("Approve", value$ -> value$.toValue(), value$ -> Approve.valueDecoder()
        .decode(value$), value$ ->
        new ContractId(value$.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()));

  public static final Choice<Instruction, Execute, Optional<Base.ContractId>> CHOICE_Execute = 
      Choice.create("Execute", value$ -> value$.toValue(), value$ -> Execute.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromOptional(v$0 ->
            new Base.ContractId(v$0.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()))
        .decode(value$));

  public static final Choice<Instruction, Allocate, Tuple2<ContractId, Optional<Base.ContractId>>> CHOICE_Allocate = 
      Choice.create("Allocate", value$ -> value$.toValue(), value$ -> Allocate.valueDecoder()
        .decode(value$), value$ ->
        Tuple2.<ContractId,
        Optional<Base.ContractId>>valueDecoder(v$0 ->
          new ContractId(v$0.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()),
        PrimitiveValueDecoders.fromOptional(v$1 ->
            new Base.ContractId(v$1.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue())))
        .decode(value$));

  public static final Choice<Instruction, Cancel, Optional<Base.ContractId>> CHOICE_Cancel = 
      Choice.create("Cancel", value$ -> value$.toValue(), value$ -> Cancel.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromOptional(v$0 ->
            new Base.ContractId(v$0.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()))
        .decode(value$));

  public static final INTERFACE_ INTERFACE = new INTERFACE_();

  private Instruction() {
  }

  public static ContractFilter<Contract<ContractId, View>> contractFilter() {
    return ContractFilter.of(INTERFACE);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Instruction> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Instruction, ?> getCompanion(
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

    default Update<Exercised<ContractId>> exerciseApprove(Approve arg) {
      return makeExerciseCmd(CHOICE_Approve, arg);
    }

    default Update<Exercised<ContractId>> exerciseApprove(Set<String> actors, Approval approval) {
      return exerciseApprove(new Approve(actors, approval));
    }

    default Update<Exercised<Optional<Base.ContractId>>> exerciseExecute(Execute arg) {
      return makeExerciseCmd(CHOICE_Execute, arg);
    }

    default Update<Exercised<Optional<Base.ContractId>>> exerciseExecute(Set<String> actors) {
      return exerciseExecute(new Execute(actors));
    }

    default Update<Exercised<Tuple2<ContractId, Optional<Base.ContractId>>>> exerciseAllocate(
        Allocate arg) {
      return makeExerciseCmd(CHOICE_Allocate, arg);
    }

    default Update<Exercised<Tuple2<ContractId, Optional<Base.ContractId>>>> exerciseAllocate(
        Set<String> actors, Allocation allocation) {
      return exerciseAllocate(new Allocate(actors, allocation));
    }

    default Update<Exercised<Optional<Base.ContractId>>> exerciseCancel(Cancel arg) {
      return makeExerciseCmd(CHOICE_Cancel, arg);
    }

    default Update<Exercised<Optional<Base.ContractId>>> exerciseCancel(Set<String> actors) {
      return exerciseCancel(new Cancel(actors));
    }
  }

  public static final class CreateAnd extends com.daml.ledger.javaapi.data.codegen.CreateAnd.ToInterface implements Exercises<CreateAndExerciseCommand> {
    public CreateAnd(ContractCompanion<?, ?, ?> companion, Template createArguments) {
      super(companion, createArguments);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Instruction, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class ByKey extends com.daml.ledger.javaapi.data.codegen.ByKey.ToInterface implements Exercises<ExerciseByKeyCommand> {
    public ByKey(ContractCompanion<?, ?, ?> companion, Value key) {
      super(companion, key);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Instruction, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class INTERFACE_ extends InterfaceCompanion<Instruction, ContractId, View> {
    INTERFACE_() {
      super(
            "com.daml.quickstart.model.daml.finance.interface$.settlement.instruction.Instruction", Instruction.TEMPLATE_ID, ContractId::new, View.valueDecoder(), List.of(CHOICE_Execute,
            CHOICE_Cancel, CHOICE_Allocate, CHOICE_Archive, CHOICE_GetView, CHOICE_Approve));
    }
  }
}
