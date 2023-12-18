package com.daml.quickstart.model.daml.finance.interface$.account.factory;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.AccountKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Remove extends DamlRecord<Remove> {
  public static final String _packageId = "615590cf0b4e5ea01cb08ef577e43d6cfcd816be060ac7a540a100c3f00b11b2";

  public final AccountKey account;

  public Remove(AccountKey account) {
    this.account = account;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Remove fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Remove> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(1,
          recordValue$);
      AccountKey account = AccountKey.valueDecoder().decode(fields$.get(0).getValue());
      return new Remove(account);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(1);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("account", this.account.toValue()));
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
    if (!(object instanceof Remove)) {
      return false;
    }
    Remove other = (Remove) object;
    return Objects.equals(this.account, other.account);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.account);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.account.factory.Remove(%s)",
        this.account);
  }
}
