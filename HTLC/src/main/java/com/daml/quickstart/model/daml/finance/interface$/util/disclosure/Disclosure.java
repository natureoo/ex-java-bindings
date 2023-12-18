package com.daml.quickstart.model.daml.finance.interface$.util.disclosure;

import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.Contract;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.da.types.Tuple2;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class Disclosure {
  public static final Identifier TEMPLATE_ID = new Identifier("9e0b0b245207257617d2a345f960be47a5a046ff26f4227bbd237f5019ea1b2e", "Daml.Finance.Interface.Util.Disclosure", "Disclosure");

  public static final Choice<Disclosure, SetObservers, ContractId> CHOICE_SetObservers = 
      Choice.create("SetObservers", value$ -> value$.toValue(), value$ ->
        SetObservers.valueDecoder().decode(value$), value$ ->
        new ContractId(value$.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()));

  public static final Choice<Disclosure, GetView, View> CHOICE_GetView = 
      Choice.create("GetView", value$ -> value$.toValue(), value$ -> GetView.valueDecoder()
        .decode(value$), value$ -> View.valueDecoder().decode(value$));

  public static final Choice<Disclosure, AddObservers, ContractId> CHOICE_AddObservers = 
      Choice.create("AddObservers", value$ -> value$.toValue(), value$ ->
        AddObservers.valueDecoder().decode(value$), value$ ->
        new ContractId(value$.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()));

  public static final Choice<Disclosure, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final Choice<Disclosure, RemoveObservers, Optional<ContractId>> CHOICE_RemoveObservers = 
      Choice.create("RemoveObservers", value$ -> value$.toValue(), value$ ->
        RemoveObservers.valueDecoder().decode(value$), value$ ->
        PrimitiveValueDecoders.fromOptional(v$0 ->
            new ContractId(v$0.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()))
        .decode(value$));

  public static final INTERFACE_ INTERFACE = new INTERFACE_();

  private Disclosure() {
  }

  public static ContractFilter<Contract<ContractId, View>> contractFilter() {
    return ContractFilter.of(INTERFACE);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Disclosure> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Disclosure, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public interface Exercises<Cmd> extends com.daml.ledger.javaapi.data.codegen.Exercises<Cmd> {
    default Update<Exercised<ContractId>> exerciseSetObservers(SetObservers arg) {
      return makeExerciseCmd(CHOICE_SetObservers, arg);
    }

    default Update<Exercised<ContractId>> exerciseSetObservers(Set<String> disclosers,
        Map<String, Set<String>> newObservers) {
      return exerciseSetObservers(new SetObservers(disclosers, newObservers));
    }

    default Update<Exercised<View>> exerciseGetView(GetView arg) {
      return makeExerciseCmd(CHOICE_GetView, arg);
    }

    default Update<Exercised<View>> exerciseGetView(String viewer) {
      return exerciseGetView(new GetView(viewer));
    }

    default Update<Exercised<ContractId>> exerciseAddObservers(AddObservers arg) {
      return makeExerciseCmd(CHOICE_AddObservers, arg);
    }

    default Update<Exercised<ContractId>> exerciseAddObservers(Set<String> disclosers,
        Tuple2<String, Set<String>> observersToAdd) {
      return exerciseAddObservers(new AddObservers(disclosers, observersToAdd));
    }

    default Update<Exercised<Unit>> exerciseArchive(Archive arg) {
      return makeExerciseCmd(CHOICE_Archive, arg);
    }

    default Update<Exercised<Optional<ContractId>>> exerciseRemoveObservers(RemoveObservers arg) {
      return makeExerciseCmd(CHOICE_RemoveObservers, arg);
    }

    default Update<Exercised<Optional<ContractId>>> exerciseRemoveObservers(Set<String> disclosers,
        Tuple2<String, Set<String>> observersToRemove) {
      return exerciseRemoveObservers(new RemoveObservers(disclosers, observersToRemove));
    }
  }

  public static final class CreateAnd extends com.daml.ledger.javaapi.data.codegen.CreateAnd.ToInterface implements Exercises<CreateAndExerciseCommand> {
    public CreateAnd(ContractCompanion<?, ?, ?> companion, Template createArguments) {
      super(companion, createArguments);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Disclosure, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class ByKey extends com.daml.ledger.javaapi.data.codegen.ByKey.ToInterface implements Exercises<ExerciseByKeyCommand> {
    public ByKey(ContractCompanion<?, ?, ?> companion, Value key) {
      super(companion, key);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, Disclosure, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class INTERFACE_ extends InterfaceCompanion<Disclosure, ContractId, View> {
    INTERFACE_() {
      super(
            "com.daml.quickstart.model.daml.finance.interface$.util.disclosure.Disclosure", Disclosure.TEMPLATE_ID, ContractId::new, View.valueDecoder(), List.of(CHOICE_RemoveObservers,
            CHOICE_AddObservers, CHOICE_Archive, CHOICE_GetView, CHOICE_SetObservers));
    }
  }
}
