package com.daml.quickstart.model.daml.finance.interface$.account.factory;

import com.daml.ledger.javaapi.data.DamlCollectors;
import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Text;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.account.account.Controllers;
import com.daml.quickstart.model.daml.finance.interface$.holding.factory.Factory;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.AccountKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Create extends DamlRecord<Create> {
  public static final String _packageId = "615590cf0b4e5ea01cb08ef577e43d6cfcd816be060ac7a540a100c3f00b11b2";

  public final AccountKey account;

  public final Factory.ContractId holdingFactoryCid;

  public final Controllers controllers;

  public final String description;

  public final Map<String, Set<String>> observers;

  public Create(AccountKey account, Factory.ContractId holdingFactoryCid, Controllers controllers,
      String description, Map<String, Set<String>> observers) {
    this.account = account;
    this.holdingFactoryCid = holdingFactoryCid;
    this.controllers = controllers;
    this.description = description;
    this.observers = observers;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Create fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Create> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(5,
          recordValue$);
      AccountKey account = AccountKey.valueDecoder().decode(fields$.get(0).getValue());
      Factory.ContractId holdingFactoryCid =
          new Factory.ContractId(fields$.get(1).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected holdingFactoryCid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      Controllers controllers = Controllers.valueDecoder().decode(fields$.get(2).getValue());
      String description = PrimitiveValueDecoders.fromText.decode(fields$.get(3).getValue());
      Map<String, Set<String>> observers = PrimitiveValueDecoders.fromGenMap(
            PrimitiveValueDecoders.fromText,
            Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty))
          .decode(fields$.get(4).getValue());
      return new Create(account, holdingFactoryCid, controllers, description, observers);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(5);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("account", this.account.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("holdingFactoryCid", this.holdingFactoryCid.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("controllers", this.controllers.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("description", new Text(this.description)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("observers", this.observers.entrySet()
        .stream()
        .collect(DamlCollectors.toDamlGenMap(v$0 -> new Text(v$0.getKey()), v$0 -> v$0.getValue().toValue(v$1 -> new Party(v$1))))));
    return new com.daml.ledger.javaapi.data.DamlRecord(fields);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof Create)) {
      return false;
    }
    Create other = (Create) object;
    return Objects.equals(this.account, other.account) &&
        Objects.equals(this.holdingFactoryCid, other.holdingFactoryCid) &&
        Objects.equals(this.controllers, other.controllers) &&
        Objects.equals(this.description, other.description) &&
        Objects.equals(this.observers, other.observers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.account, this.holdingFactoryCid, this.controllers, this.description,
        this.observers);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.account.factory.Create(%s, %s, %s, %s, %s)",
        this.account, this.holdingFactoryCid, this.controllers, this.description, this.observers);
  }
}
