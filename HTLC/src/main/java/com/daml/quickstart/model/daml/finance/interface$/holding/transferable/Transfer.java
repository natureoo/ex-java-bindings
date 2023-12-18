package com.daml.quickstart.model.daml.finance.interface$.holding.transferable;

import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.AccountKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Transfer extends DamlRecord<Transfer> {
  public static final String _packageId = "95644d5c6ff8c9a433820d694916d86d5e94e1418880b66bf0b3e5103dbc0e09";

  public final Set<String> actors;

  public final AccountKey newOwnerAccount;

  public Transfer(Set<String> actors, AccountKey newOwnerAccount) {
    this.actors = actors;
    this.newOwnerAccount = newOwnerAccount;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Transfer fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Transfer> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(2,
          recordValue$);
      Set<String> actors = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(0).getValue());
      AccountKey newOwnerAccount = AccountKey.valueDecoder().decode(fields$.get(1).getValue());
      return new Transfer(actors, newOwnerAccount);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(2);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("actors", this.actors.toValue(v$0 -> new Party(v$0))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("newOwnerAccount", this.newOwnerAccount.toValue()));
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
    if (!(object instanceof Transfer)) {
      return false;
    }
    Transfer other = (Transfer) object;
    return Objects.equals(this.actors, other.actors) &&
        Objects.equals(this.newOwnerAccount, other.newOwnerAccount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.actors, this.newOwnerAccount);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.holding.transferable.Transfer(%s, %s)",
        this.actors, this.newOwnerAccount);
  }
}
