package com.daml.quickstart.model.daml.finance.interface$.types.common.types;

import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountKey extends DamlRecord<AccountKey> {
  public static final String _packageId = "361a5742b581d2d0dc1378f905a7f0ff692d3eb0d79b9fde51e34390813cf7d4";

  public final String custodian;

  public final String owner;

  public final Id id;

  public AccountKey(String custodian, String owner, Id id) {
    this.custodian = custodian;
    this.owner = owner;
    this.id = id;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static AccountKey fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<AccountKey> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(3,
          recordValue$);
      String custodian = PrimitiveValueDecoders.fromParty.decode(fields$.get(0).getValue());
      String owner = PrimitiveValueDecoders.fromParty.decode(fields$.get(1).getValue());
      Id id = Id.valueDecoder().decode(fields$.get(2).getValue());
      return new AccountKey(custodian, owner, id);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(3);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("custodian", new Party(this.custodian)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("owner", new Party(this.owner)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("id", this.id.toValue()));
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
    if (!(object instanceof AccountKey)) {
      return false;
    }
    AccountKey other = (AccountKey) object;
    return Objects.equals(this.custodian, other.custodian) &&
        Objects.equals(this.owner, other.owner) && Objects.equals(this.id, other.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.custodian, this.owner, this.id);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.types.common.types.AccountKey(%s, %s, %s)",
        this.custodian, this.owner, this.id);
  }
}
