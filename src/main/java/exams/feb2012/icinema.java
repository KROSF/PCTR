package feb2012;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * icinema
 */
public interface icinema extends Remote {
  public String buyTicket(String movie, int seat) throws RemoteException;
}