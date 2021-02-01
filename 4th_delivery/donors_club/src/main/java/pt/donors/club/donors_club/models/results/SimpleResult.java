package pt.donors.club.donors_club.models.results;

public class SimpleResult {
  private String result;
  private Object object;

  public SimpleResult(String result, Object object) {
    this.result = result;
    this.object = object;
  }

  public String getResult() {
    return result;
  }

  public Object getObject() {
    return object;
  }
}
