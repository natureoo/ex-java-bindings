package com.daml.quickstart.model.daml.finance.interface$.account.account;

import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Text;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.daml.finance.interface$.holding.factory.Factory;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class View extends DamlRecord<View> {
  public static final String _packageId = "615590cf0b4e5ea01cb08ef577e43d6cfcd816be060ac7a540a100c3f00b11b2";

  public final String custodian;

  public final String owner;

  public final Id id;

  public final String description;

  public final Factory.ContractId holdingFactoryCid;

  public final Controllers controllers;

  public View(String custodian, String owner, Id id, String description,
      Factory.ContractId holdingFactoryCid, Controllers controllers) {
    this.custodian = custodian;
    this.owner = owner;
    this.id = id;
    this.description = description;
    this.holdingFactoryCid = holdingFactoryCid;
    this.controllers = controllers;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static View fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<View> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(6,
          recordValue$);
      String custodian = PrimitiveValueDecoders.fromParty.decode(fields$.get(0).getValue());
      String owner = PrimitiveValueDecoders.fromParty.decode(fields$.get(1).getValue());
      Id id = Id.valueDecoder().decode(fields$.get(2).getValue());
      String description = PrimitiveValueDecoders.fromText.decode(fields$.get(3).getValue());
      Factory.ContractId holdingFactoryCid =
          new Factory.ContractId(fields$.get(4).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected holdingFactoryCid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      Controllers controllers = Controllers.valueDecoder().decode(fields$.get(5).getValue());
      return new View(custodian, owner, id, description, holdingFactoryCid, controllers);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(6);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("custodian", new Party(this.custodian)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("owner", new Party(this.owner)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("id", this.id.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("description", new Text(this.description)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("holdingFactoryCid", this.holdingFactoryCid.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("controllers", this.controllers.toValue()));
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
    if (!(object instanceof View)) {
      return false;
    }
    View other = (View) object;
    return Objects.equals(this.custodian, other.custodian) &&
        Objects.equals(this.owner, other.owner) && Objects.equals(this.id, other.id) &&
        Objects.equals(this.description, other.description) &&
        Objects.equals(this.holdingFactoryCid, other.holdingFactoryCid) &&
        Objects.equals(this.controllers, other.controllers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.custodian, this.owner, this.id, this.description,
        this.holdingFactoryCid, this.controllers);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.account.account.View(%s, %s, %s, %s, %s, %s)",
        this.custodian, this.owner, this.id, this.description, this.holdingFactoryCid,
        this.controllers);
  }
}
