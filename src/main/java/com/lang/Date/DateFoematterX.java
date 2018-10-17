
package com.lang.Date;

public class DateFoematterX{
  private boolean era;

  public  static class DateFormatterBuilder {
    private boolean era;

   public  DateFormatterBuilder  (){

   }

   public DateFormatterBuilder withEra(){
     era=true;
     return this;
    }

    public DateFoematterX build(){
      DateFoematterX dateFoematterX = new DateFoematterX();
      dateFoematterX.era=this.era;
      return dateFoematterX;
    }

  }
}

