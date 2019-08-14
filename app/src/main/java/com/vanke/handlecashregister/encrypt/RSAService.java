package com.vanke.handlecashregister.encrypt;


public class RSAService {

    //        public static final RSA RSA = new RSA("<RSAKeyValue><Modulus>b2kBrWhcWE5rSovNtXDn9bRTXe1atGxGaGGyC80ytseqTrai+GnHqtswypxrqh3Pgigv48qBJDV7g9ZjTaDvnTTgUFFA0GZbri8bxLJBv5XsvwnbJkd9FqYO/8+dUYJhbsPtFWSaXCMVNqFgcmdrxP7XaN4cupfVPCWxd7MYtv8=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>", "<RSAKeyValue><Modulus>ULR6ekEXCMS1gCl9fy6v/K3+Xz0ubpaB/TKexkbFXbLEmtIWgM6E4IqOKqK11py2ANZrhcCJSFVlKpY8IG/KfhyIFsP7g8QTnWIh5R406H691kr80NZSs4n42EV9SQRG/z01b4MLsd0PVceGW1dCdnzedAnUesX8o4dE3qYtnFc=</Modulus><Exponent>AQAB</Exponent><P>lB+jUZ4So+nyC27l+1H/MfddRbCoKsQWedoGdZq50bHtOJLOOTxMskJhsM/7nzEqbX00Nb/d2vgzD/GZiNONkw==</P><Q>i3s6Ec2RrlfefEJ5F3y6rZkUc6h7jkvrtrn+zt7XltSS7K39M2WKEX50hczyV45V42rJN2WqGUHeumB2VMNQrQ==</Q><DP>SSnTbrYIeQRONQ2C9X9L8O9BF/IpLm6Kt4dUl3RkgkpDZSVGTYkWTRpyzHX+0GPllA06462wtL7R4fcxIh90lw==</DP><DQ>NYWnlsVHLZtSKGRpivW1vH+NNYRlRQkk/g0JFeC04Y/YgTGSg3JnNKdNlzo5lBduJcIITFWam+6GxQXW4lfAUQ==</DQ><InverseQ>Ri387F5sa2TKvyLmFfsxHzygKSrx+ZWcEXwqtGdyKo9IA8EWQYXAmRR1zyZNCiq9OnWfPkwoqbSnEqxiEUN8yg==</InverseQ><D>Kr0Ix0lE+33AaFiQfxOoWvWBacoqlqHZwM5XysSbrF0JRLvQ8vFq6MY3N1jnVvmyYIssca5ehRHD+2OH/91zq/D9Z489YaUS55seT74lpLbjAQknVnvSX7zpadx1rXwr2qJDCcpc0Ix8R43kL+oWMGtjn7il5S00Dp2zL9KGEbE=</D></RSAKeyValue>");
//    public static final RSA RSA = new RSA(  Config.CLIENT_PUBLIC_KEY_CRM,Config.SERVER_PRIVATE_KEY_CRM);
//    public static final RSA RSA = new RSA(Config.CLIENT_PUBLIC_KEY_CRM, Config.CLIENT_PRIVATE_KEY_CRM);
    public static RSA CRM_RSA = new RSA(Config.SERVER_PUBLIC_KEY_CRM, Config.SERVER_PRIVATE_KEY_CRM);
    //    public static final RSA RSA = new RSA(Config.PUBLIC_KEY_CMP2, Config.PRIVATE_KEY_LIANMENG);
    public static RSA CPM_RSA = new RSA(Config.PUBLIC_KEY, Config.PRIVATE_KEY_CMP);


    public static String cmpDecrypt(String paramString) {
        return CPM_RSA.decrypt(paramString);
    }

    public static String cmpEncrypt(String paramString) {
        return CPM_RSA.encrypt(paramString);
    }


    public static String crmDecrypt(String paramString) {
        return CRM_RSA.decrypt(paramString);
    }

    public static String crmEncrypt(String paramString) {
        return CRM_RSA.encrypt(paramString);
    }
}
