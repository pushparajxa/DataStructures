
package com.lang.ldap;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

/**
 * Example code for retrieving a Users Primary Group
 * from Microsoft Active Directory via. its LDAP API
 *
 * @author Adam Retter <adam.retter@googlemail.com>
 */
public class LdapTest {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws NamingException {

 //   String ldapAdServer = "ldap://ldap-v2-replica.stg.rivigo.com:389";
    String ldapAdServer = "ldap://localhost:389";
     String ldapSearchBase = "cn=config";

    final String ldapUsername = "cn=admin,dc=rivigo,dc=com";
    final String ldapPassword = "admin";



    Hashtable<String, Object> env = new Hashtable<String, Object>();
    env.put(Context.SECURITY_AUTHENTICATION, "simple");
    if(ldapUsername != null) {
      env.put(Context.SECURITY_PRINCIPAL, ldapUsername);
    }
    if(ldapPassword != null) {
      env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
    }
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, ldapAdServer);

    //ensures that objectSID attribute values
    //will be returned as a byte[] instead of a String

    // the following is helpful in debugging errors
    //env.put("com.sun.jndi.ldap.trace.ber", System.err);

    DirContext ctx = new InitialDirContext(env);


  /*  Hashtable<String, Object> env2 = new Hashtable<String, Object>();
    env2.put(Context.SECURITY_AUTHENTICATION, "simple");
    if(ldapUsername != null) {
      env2.put(Context.SECURITY_PRINCIPAL, ldapUsername);
    }
    if(ldapPassword != null) {
      env2.put(Context.SECURITY_CREDENTIALS, ldapPassword);
    }
    env2.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env2.put(Context.PROVIDER_URL, ldapAdServer);

    DirContext ctx2 = new InitialDirContext(env2); */

    LdapTest ldap = new LdapTest();

     ldap.getResults(ctx);

    System.out.println("Second");

    /*ldap.getResults(ctx2);*/

  }

  private void getResults(DirContext ctx) throws NamingException {


    String searchFilter = "olcDatabase={1}hdb";

    SearchControls searchControls = new SearchControls();
    searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

    NamingEnumeration<SearchResult> results = ctx.search("cn=config", searchFilter, searchControls);
    while(results.hasMoreElements()){
      System.out.println("counting");
      SearchResult searchResult = (SearchResult)results.nextElement();
      System.out.println("olcDbIndex=" + searchResult.getAttributes().get("olcDbIndex"));
    }


  }


}