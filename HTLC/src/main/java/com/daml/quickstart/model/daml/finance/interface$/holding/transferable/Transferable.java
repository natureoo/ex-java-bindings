package com.daml.quickstart.model.daml.finance.interface$.holding.transferable;

import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.Contract;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.AccountKey;

import java.util.List;

public final class Transferable {
  public static final Identifier TEMPLATE_ID = new Identifier("95644d5c6ff8c9a433820d694916d86d5e94e1418880b66bf0b3e5103dbc0e09", "Daml.Finance.Interface.Holding.Transferable", "Transferable");

  public static final Choice<Transferable, GetView, View> CHOICE_GetView = 
      Choice.create("GetView", value$ -> value$.toValue(), value$ -> GetView.valueDecoder()
        .decode(value$), value$ -> View.valueDecoder().decode(value$));

  public static final Choice<Transferable, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final Choice<Transferable, Transfer, ContractId> CHOICE_Transfer = 
      Choice.create("Transfer", value$ -> value$.toValue(), value$ -> Transfer.valueDecoder()
        .decode(value$), value$ ->
        new ContractId(value$.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()));

  public static final INTERFACE_ INTERFACE = new INTERFACE_();

  private Transferable() {
  }

  public static ContractFilter<Contract<ContractId, View>> contractFilter() {
    return ContractFilter.of(INTERFACE);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Transferable> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Transferable, ?> getCompanion(
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

    default Update<Exercised<ContractId>> exerciseTransfer(Transfer arg) {
      return makeExerciseCmd(CHOICE_Transfer, arg);
    }

    default Update<Exercised<ContractId>> exerciseTransfer(Set<String> actors,
        AccountKey newOwnerAccount) {
      return exerciseTransfer(new Transfer(actors, newOwnerAccount));
    }
  }

  public static final class CreateAnd extends com.daml.ledger.javaapi.data.codegen.CreateAnd.ToInterface implements Exercises<CreateAndExerciseCommand> {
    public CreateAnd(ContractCompanion<?, ?, ?> companion, Template createArguments) {
      super(companion, createArguments);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Transferable, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class ByKey extends com.daml.ledger.javaapi.data.codegen.ByKey.ToInterface implements Exercises<ExerciseByKeyCommand> {
    public ByKey(ContractCompanion<?, ?, ?> companion, Value key) {
      super(companion, key);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Transferable, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class INTERFACE_ extends InterfaceCompanion<Transferable, ContractId, View> {
    INTERFACE_() {
      super(
            "com.daml.quickstart.model.daml.finance.interface$.holding.transferable.Transferable", Transferable.TEMPLATE_ID, ContractId::new, View.valueDecoder(), List.of(CHOICE_GetView,
            CHOICE_Archive, CHOICE_Transfer));
    }
  }
}
