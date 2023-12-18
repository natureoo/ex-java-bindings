package com.daml.quickstart.model.daml.finance.interface$.holding.fungible;

import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.Contract;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;

import java.math.BigDecimal;
import java.util.List;

public final class Fungible {
  public static final Identifier TEMPLATE_ID = new Identifier("95644d5c6ff8c9a433820d694916d86d5e94e1418880b66bf0b3e5103dbc0e09", "Daml.Finance.Interface.Holding.Fungible", "Fungible");

  public static final Choice<Fungible, GetView, View> CHOICE_GetView = 
      Choice.create("GetView", value$ -> value$.toValue(), value$ -> GetView.valueDecoder()
        .decode(value$), value$ -> View.valueDecoder().decode(value$));

  public static final Choice<Fungible, ArchiveFungible, Unit> CHOICE_ArchiveFungible = 
      Choice.create("ArchiveFungible", value$ -> value$.toValue(), value$ ->
        ArchiveFungible.valueDecoder().decode(value$), value$ -> PrimitiveValueDecoders.fromUnit
        .decode(value$));

  public static final Choice<Fungible, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final Choice<Fungible, Split, SplitResult> CHOICE_Split = 
      Choice.create("Split", value$ -> value$.toValue(), value$ -> Split.valueDecoder()
        .decode(value$), value$ -> SplitResult.valueDecoder().decode(value$));

  public static final Choice<Fungible, Merge, ContractId> CHOICE_Merge = 
      Choice.create("Merge", value$ -> value$.toValue(), value$ -> Merge.valueDecoder()
        .decode(value$), value$ ->
        new ContractId(value$.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()));

  public static final INTERFACE_ INTERFACE = new INTERFACE_();

  private Fungible() {
  }

  public static ContractFilter<Contract<ContractId, View>> contractFilter() {
    return ContractFilter.of(INTERFACE);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Fungible> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Fungible, ?> getCompanion(
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

    default Update<Exercised<Unit>> exerciseArchiveFungible(ArchiveFungible arg) {
      return makeExerciseCmd(CHOICE_ArchiveFungible, arg);
    }

    default Update<Exercised<Unit>> exerciseArchiveFungible() {
      return exerciseArchiveFungible(new ArchiveFungible());
    }

    default Update<Exercised<Unit>> exerciseArchive(Archive arg) {
      return makeExerciseCmd(CHOICE_Archive, arg);
    }

    default Update<Exercised<SplitResult>> exerciseSplit(Split arg) {
      return makeExerciseCmd(CHOICE_Split, arg);
    }

    default Update<Exercised<SplitResult>> exerciseSplit(List<BigDecimal> amounts) {
      return exerciseSplit(new Split(amounts));
    }

    default Update<Exercised<ContractId>> exerciseMerge(Merge arg) {
      return makeExerciseCmd(CHOICE_Merge, arg);
    }

    default Update<Exercised<ContractId>> exerciseMerge(List<ContractId> fungibleCids) {
      return exerciseMerge(new Merge(fungibleCids));
    }
  }

  public static final class CreateAnd extends com.daml.ledger.javaapi.data.codegen.CreateAnd.ToInterface implements Exercises<CreateAndExerciseCommand> {
    public CreateAnd(ContractCompanion<?, ?, ?> companion, Template createArguments) {
      super(companion, createArguments);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Fungible, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class ByKey extends com.daml.ledger.javaapi.data.codegen.ByKey.ToInterface implements Exercises<ExerciseByKeyCommand> {
    public ByKey(ContractCompanion<?, ?, ?> companion, Value key) {
      super(companion, key);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Fungible, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class INTERFACE_ extends InterfaceCompanion<Fungible, ContractId, View> {
    INTERFACE_() {
      super(
            "com.daml.quickstart.model.daml.finance.interface$.holding.fungible.Fungible", Fungible.TEMPLATE_ID, ContractId::new, View.valueDecoder(), List.of(CHOICE_Split,
            CHOICE_Merge, CHOICE_Archive, CHOICE_GetView, CHOICE_ArchiveFungible));
    }
  }
}
