package com.daml.quickstart.model.daml.finance.interface$.settlement.routeprovider;

import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.Contract;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.RoutedStep;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.Step;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Id;

import java.util.List;
import java.util.Optional;

public final class RouteProvider {
  public static final Identifier TEMPLATE_ID = new Identifier("4b5bf7a6465e5e11800a03c131a5759565edd3ff9cfc43f41d1f7fbc72255587", "Daml.Finance.Interface.Settlement.RouteProvider", "RouteProvider");

  public static final Choice<RouteProvider, GetView, View> CHOICE_GetView = 
      Choice.create("GetView", value$ -> value$.toValue(), value$ -> GetView.valueDecoder()
        .decode(value$), value$ -> View.valueDecoder().decode(value$));

  public static final Choice<RouteProvider, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final Choice<RouteProvider, Discover, List<RoutedStep>> CHOICE_Discover = 
      Choice.create("Discover", value$ -> value$.toValue(), value$ -> Discover.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromList(RoutedStep.valueDecoder())
        .decode(value$));

  public static final INTERFACE_ INTERFACE = new INTERFACE_();

  private RouteProvider() {
  }

  public static ContractFilter<Contract<ContractId, View>> contractFilter() {
    return ContractFilter.of(INTERFACE);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<RouteProvider> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, RouteProvider, ?> getCompanion(
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

    default Update<Exercised<List<RoutedStep>>> exerciseDiscover(Discover arg) {
      return makeExerciseCmd(CHOICE_Discover, arg);
    }

    default Update<Exercised<List<RoutedStep>>> exerciseDiscover(Set<String> discoverors,
        Optional<Id> contextId, List<Step> steps) {
      return exerciseDiscover(new Discover(discoverors, contextId, steps));
    }
  }

  public static final class CreateAnd extends com.daml.ledger.javaapi.data.codegen.CreateAnd.ToInterface implements Exercises<CreateAndExerciseCommand> {
    public CreateAnd(ContractCompanion<?, ?, ?> companion, Template createArguments) {
      super(companion, createArguments);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, RouteProvider, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class ByKey extends com.daml.ledger.javaapi.data.codegen.ByKey.ToInterface implements Exercises<ExerciseByKeyCommand> {
    public ByKey(ContractCompanion<?, ?, ?> companion, Value key) {
      super(companion, key);
    }

    @Override
    protected ContractTypeCompanion<? extends Contract<ContractId, ?>, ContractId, RouteProvider, ?> getCompanion(
        ) {
      return INTERFACE;
    }
  }

  public static final class INTERFACE_ extends InterfaceCompanion<RouteProvider, ContractId, View> {
    INTERFACE_() {
      super(
            "com.daml.quickstart.model.daml.finance.interface$.settlement.routeprovider.RouteProvider", RouteProvider.TEMPLATE_ID, ContractId::new, View.valueDecoder(), List.of(CHOICE_GetView,
            CHOICE_Archive, CHOICE_Discover));
    }
  }
}
