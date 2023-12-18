package com.daml.quickstart.model.workflow.createaccount;

import com.daml.ledger.javaapi.data.DamlCollectors;
import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Text;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.daml.finance.interface$.account.factory.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Accept extends DamlRecord<Accept> {
  public static final String _packageId = "91deeb52981e7064dbcf05e8dd2a81ef0d8a25fe4aaff70e56ec84fcb11aa28d";

  public final String label;

  public final String description;

  public final Factory.ContractId accountFactoryCid;

  public final com.daml.quickstart.model.daml.finance.interface$.holding.factory.Factory.ContractId holdingFactoryCid;

  public final List<String> observers;

  public Accept(String label, String description, Factory.ContractId accountFactoryCid,
      com.daml.quickstart.model.daml.finance.interface$.holding.factory.Factory.ContractId holdingFactoryCid,
      List<String> observers) {
    this.label = label;
    this.description = description;
    this.accountFactoryCid = accountFactoryCid;
    this.holdingFactoryCid = holdingFactoryCid;
    this.observers = observers;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Accept fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Accept> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(5,
          recordValue$);
      String label = PrimitiveValueDecoders.fromText.decode(fields$.get(0).getValue());
      String description = PrimitiveValueDecoders.fromText.decode(fields$.get(1).getValue());
      Factory.ContractId accountFactoryCid =
          new Factory.ContractId(fields$.get(2).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected accountFactoryCid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      com.daml.quickstart.model.daml.finance.interface$.holding.factory.Factory.ContractId holdingFactoryCid =
          new com.daml.quickstart.model.daml.finance.interface$.holding.factory.Factory.ContractId(fields$.get(3).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected holdingFactoryCid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      List<String> observers = PrimitiveValueDecoders.fromList(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(4).getValue());
      return new Accept(label, description, accountFactoryCid, holdingFactoryCid, observers);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(5);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("label", new Text(this.label)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("description", new Text(this.description)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("accountFactoryCid", this.accountFactoryCid.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("holdingFactoryCid", this.holdingFactoryCid.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("observers", this.observers.stream().collect(DamlCollectors.toDamlList(v$0 -> new Party(v$0)))));
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
    if (!(object instanceof Accept)) {
      return false;
    }
    Accept other = (Accept) object;
    return Objects.equals(this.label, other.label) &&
        Objects.equals(this.description, other.description) &&
        Objects.equals(this.accountFactoryCid, other.accountFactoryCid) &&
        Objects.equals(this.holdingFactoryCid, other.holdingFactoryCid) &&
        Objects.equals(this.observers, other.observers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.label, this.description, this.accountFactoryCid,
        this.holdingFactoryCid, this.observers);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.workflow.createaccount.Accept(%s, %s, %s, %s, %s)",
        this.label, this.description, this.accountFactoryCid, this.holdingFactoryCid,
        this.observers);
  }
}
