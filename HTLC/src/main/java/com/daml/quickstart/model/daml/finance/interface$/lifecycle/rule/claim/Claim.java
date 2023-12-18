package com.daml.quickstart.model.daml.finance.interface$.lifecycle.rule.claim;

import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.Contract;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.daml.finance.interface$.holding.base.Base;
import com.daml.quickstart.model.daml.finance.interface$.lifecycle.effect.Effect;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Id;

import java.util.List;

public final class Claim {
  public static final Identifier TEMPLATE_ID = new Identifier("20176559c50772de2d90e9779dcfad78bc0edf2a949454c8cb78dde8cb89f780", "Daml.Finance.Interface.Lifecycle.Rule.Claim", "Claim");

  public static final Choice<Claim, ClaimEffect, ClaimResult> CHOICE_ClaimEffect = 
      Choice.create("ClaimEffect", value$ -> value$.toValue(), value$ -> ClaimEffect.valueDecoder()
        .decode(value$), value$ -> ClaimResult.valueDecoder().decode(value$));

  public static final Choice<Claim, GetView, View> CHOICE_GetView = 
      Choice.create("GetView", value$ -> value$.toValue(), value$ -> GetView.valueDecoder()
        .decode(value$), value$ -> View.valueDecoder().decode(value$));

  public static final Choice<Claim, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final INTERFACE_ INTERFACE = new INTERFACE_();

  private Claim() {
  }

  public static ContractFilter<Contract<ContractId, View>> contractFilter() {
    return ContractFilter.of(INTERFACE);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Claim> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Claim, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public interface Exercises<Cmd> extends com.daml.ledger.javaapi.data.codegen.Exercises<Cmd> {
    default Update<Exercised<ClaimResult>> exerciseClaimEffect(ClaimEffect arg) {
      return makeExerciseCmd(CHOICE_ClaimEffect, arg);
    }

    default Update<Exercised<ClaimResult>> exerciseClaimEffect(String claimer,
        List<Base.ContractId> holdingCids, Effect.ContractId effectCid, Id batchId) {
      return exerciseClaimEffect(new ClaimEffect(claimer, holdingCids, effectCid, batchId));
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
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Claim, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class ByKey extends com.daml.ledger.javaapi.data.codegen.ByKey.ToInterface implements Exercises<ExerciseByKeyCommand> {
    public ByKey(ContractCompanion<?, ?, ?> companion, Value key) {
      super(companion, key);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Claim, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class INTERFACE_ extends InterfaceCompanion<Claim, ContractId, View> {
    INTERFACE_() {
      super(
            "com.daml.quickstart.model.daml.finance.interface$.lifecycle.rule.claim.Claim", Claim.TEMPLATE_ID, ContractId::new, View.valueDecoder(), List.of(CHOICE_ClaimEffect,
            CHOICE_GetView, CHOICE_Archive));
    }
  }
}
