package com.daml.quickstart.model.synfini.htlc;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.AccountKey;
import java.lang.Deprecated;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HTLCProposal_Accept extends DamlRecord<HTLCProposal_Accept> {
  public static final String _packageId = "ed470a2759bcfa1304b57e1221c2f15bfe11e4b573ad92d640a73ccc1dccbda5";

  public final AccountKey receiverAccount;

  public HTLCProposal_Accept(AccountKey receiverAccount) {
    this.receiverAccount = receiverAccount;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static HTLCProposal_Accept fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<HTLCProposal_Accept> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(1,
          recordValue$);
      AccountKey receiverAccount = AccountKey.valueDecoder().decode(fields$.get(0).getValue());
      return new HTLCProposal_Accept(receiverAccount);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(1);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("receiverAccount", this.receiverAccount.toValue()));
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
    if (!(object instanceof HTLCProposal_Accept)) {
      return false;
    }
    HTLCProposal_Accept other = (HTLCProposal_Accept) object;
    return Objects.equals(this.receiverAccount, other.receiverAccount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.receiverAccount);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.synfini.htlc.HTLCProposal_Accept(%s)",
        this.receiverAccount);
  }
}
