
package th.co.aerothai.document.ldap;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPMessage;
import com.novell.ldap.LDAPSearchQueue;
import com.novell.ldap.LDAPSearchResult;
import com.novell.ldap.LDAPSearchResults;

/*
 * Created on 10 ат.б. 2550
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author manop
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class LDAPConnect {

	//private String ldapHost = "mkfs01.aerothai.co.th";
	private String ldapHost = "172.16.24.13";
	private int ldapPort = LDAPConnection.DEFAULT_PORT;
	private int ldapVersion = LDAPConnection.LDAP_V3;
	private LDAPConnection connection;
	
	public LDAPConnect() {
		connection = new LDAPConnection();
	}
		
	public void disconnect() {
		try {
			connection.disconnect();
		} catch (LDAPException e) {
			e.printStackTrace();
		}
	}

	private Vector getAttribute(String entryDN,String attr){
		Vector v=new Vector();
		LDAPSearchResults searchResults;		
		LDAPEntry entry = null;
		try    {
			searchResults =
				connection.search(  entryDN,	LDAPConnection.SCOPE_BASE, "",null, false);
				entry = searchResults.next();
		}catch(LDAPException e) {
				//e.printStackTrace();
			return v;
		}

		//System.out.println(entry);
		LDAPAttributeSet attributeSet = entry.getAttributeSet();
		//System.out.println(attributeSet);
		Iterator allAttributes = attributeSet.iterator();

		while(allAttributes.hasNext()) {
				LDAPAttribute attribute =
							(LDAPAttribute)allAttributes.next();
			 String attributeName = attribute.getName();
			 if (attributeName.equalsIgnoreCase(attr)) {
				Enumeration en = attribute.getStringValues();
				for (;en.hasMoreElements();){
					v.add(en.nextElement());
				}
			 }
		}
		return v;
	}

	public LDAPUser login(String aUserName, String aPassword) {
		LDAPUser onlineUser = null;
		try {
			connection.connect(ldapHost, ldapPort);
			System.out.println("Connect Successfull");
			System.out.println(aUserName);
			LDAPSearchQueue searchResults = connection.search("o=Aerothai",LDAPConnection.SCOPE_SUB,"cn="+aUserName,new String[]{ LDAPConnection.NO_ATTRS},true, (LDAPSearchQueue)null);
			LDAPMessage message ;
			message = searchResults.getResponse();
			if ( message instanceof LDAPSearchResult  ) {
				LDAPEntry entry =  ((LDAPSearchResult)message).getEntry();
				String dn = entry.getDN();
				String[] userDn = dn.split(",");
				String fullDn = userDn[0] + "," + userDn[1] + "," + userDn[2] + ",o=Aerothai";
				connection.bind(ldapVersion, fullDn, aPassword.getBytes("UTF8"));
				System.out.println("Bind Successfull");
				onlineUser = new LDAPUser();
				try {
					onlineUser.setFirstName((String)getAttribute(fullDn,"givenName").elementAt(0));
					onlineUser.setLastName((String)getAttribute(fullDn,"sn").elementAt(0));
					try {
						Integer.parseInt((String)getAttribute(fullDn,"cn").elementAt(1));
						onlineUser.setEmployeeCode((String)getAttribute(fullDn,"cn").elementAt(1));
					} catch (NumberFormatException e) {
						onlineUser.setEmployeeCode((String)getAttribute(fullDn,"cn").elementAt(0));
					}
					onlineUser.setDepartment( (String) getAttribute(fullDn,"ou").elementAt(0));
					onlineUser.setLocation( (userDn[2].split("="))[1] );
				}	catch (ArrayIndexOutOfBoundsException e1) {
						e1.printStackTrace();
						onlineUser = null;		
				}
			} else {
				disconnect();
			}
			disconnect();
		} catch (LDAPException e) {
			e.printStackTrace();		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();		
		} finally {
			disconnect();
		}
		return onlineUser;
	}
	
	public LDAPUser getUserData(String username){
		LDAPUser onlineUser = null;
		try {
			connection.connect(ldapHost, ldapPort);
			LDAPSearchQueue searchResults = connection.search("o=Aerothai",LDAPConnection.SCOPE_SUB,"cn="+username,new String[]{ LDAPConnection.NO_ATTRS},true, (LDAPSearchQueue)null);
			LDAPMessage message ;
			message = searchResults.getResponse();
			
			if ( message instanceof LDAPSearchResult  ) {
				LDAPEntry entry =  ((LDAPSearchResult)message).getEntry();
				String dn = entry.getDN();
				String[] userDn = dn.split(",");
				String fullDn = userDn[0] + "," + userDn[1] + "," + userDn[2] + ",o=Aerothai";
				connection.bind(ldapVersion, fullDn, "".getBytes("UTF8"));
				System.out.println("Bind Successfull");
				onlineUser = new LDAPUser();
				try {
					onlineUser.setFirstName((String)getAttribute(fullDn,"givenName").elementAt(0));
					onlineUser.setLastName((String)getAttribute(fullDn,"sn").elementAt(0));
					try {
						Integer.parseInt((String)getAttribute(fullDn,"cn").elementAt(1));
						onlineUser.setEmployeeCode((String)getAttribute(fullDn,"cn").elementAt(1));
					} catch (NumberFormatException e) {
						onlineUser.setEmployeeCode((String)getAttribute(fullDn,"cn").elementAt(0));
					}
					onlineUser.setDepartment( (String) getAttribute(fullDn,"ou").elementAt(0));
					onlineUser.setLocation( (userDn[2].split("="))[1] );
				}	catch (ArrayIndexOutOfBoundsException e1) {
						e1.printStackTrace();
						onlineUser = null;								
				}
			} else {
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return onlineUser;
	}

	public static void main(String[] args) {
		LDAPConnect connect = new LDAPConnect();
		LDAPUser user = connect.getUserData("");
		System.out.println(user.getEmployeeCode());
	}

}
