package com.vanke.handlecashregister.encrypt;

public class Request<T>
{
  public T data;
  public String sign;
  public long timestamp;

  public Request()
  {
  }

  public Request(T paramT)
  {
    this.data = paramT;
  }
}

/* Location:           C:\Users\IT\Desktop\ApkIDE\classes2_dex2jar.jar
 * Qualified Name:     com.vanke.hisense.webApi.Request
 * JD-Core Version:    0.6.2
 */