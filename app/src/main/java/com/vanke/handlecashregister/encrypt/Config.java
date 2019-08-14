package com.vanke.handlecashregister.encrypt;

/**
 * Description:
 * Copyright: Copyright (c) 2017
 * Company: www.kuaidijin.com
 *
 * @author kangwencai
 * @version 1.0
 * @date 2019/8/8
 */
public class Config {


    public static final String PRIVATE_KEY_LIANMENG_M1CARD = "<RSAKeyValue><Modulus>ULR6ekEXCMS1gCl9fy6v/K3+Xz0ubpaB/TKexkbFXbLEmtIWgM6E4IqOKqK11py2ANZrhcCJSFVlKpY8IG/KfhyIFsP7g8QTnWIh5R406H691kr80NZSs4n42EV9SQRG/z01b4MLsd0PVceGW1dCdnzedAnUesX8o4dE3qYtnFc=</Modulus><Exponent>AQAB</Exponent><P>lB+jUZ4So+nyC27l+1H/MfddRbCoKsQWedoGdZq50bHtOJLOOTxMskJhsM/7nzEqbX00Nb/d2vgzD/GZiNONkw==</P><Q>i3s6Ec2RrlfefEJ5F3y6rZkUc6h7jkvrtrn+zt7XltSS7K39M2WKEX50hczyV45V42rJN2WqGUHeumB2VMNQrQ==</Q><DP>SSnTbrYIeQRONQ2C9X9L8O9BF/IpLm6Kt4dUl3RkgkpDZSVGTYkWTRpyzHX+0GPllA06462wtL7R4fcxIh90lw==</DP><DQ>NYWnlsVHLZtSKGRpivW1vH+NNYRlRQkk/g0JFeC04Y/YgTGSg3JnNKdNlzo5lBduJcIITFWam+6GxQXW4lfAUQ==</DQ><InverseQ>Ri387F5sa2TKvyLmFfsxHzygKSrx+ZWcEXwqtGdyKo9IA8EWQYXAmRR1zyZNCiq9OnWfPkwoqbSnEqxiEUN8yg==</InverseQ><D>Kr0Ix0lE+33AaFiQfxOoWvWBacoqlqHZwM5XysSbrF0JRLvQ8vFq6MY3N1jnVvmyYIssca5ehRHD+2OH/91zq/D9Z489YaUS55seT74lpLbjAQknVnvSX7zpadx1rXwr2qJDCcpc0Ix8R43kL+oWMGtjn7il5S00Dp2zL9KGEbE=</D></RSAKeyValue>";
    // 客户端的公钥
    public static final String PUBLIC_KEY_CMP = "<RSAKeyValue><Modulus>yxRBOGjrXlZQprBSHTwwitl9SoyEAtLm9Lnl8mws4XkJ9/6uKpkXZDuYM1u4hNOnGuRMPFXn2zksPc5KKIeH894i0JtGNyGFnpZCXQ1DefkD4XTATzXQL2osvP1UBy0aA8ZWY5brNgOetq4chfGoMXaH9ZHmZQsYmygbIklmFDc=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";


    /*************************************************************/
    /**
     * todo:
     * fullReduction 对pluCode & pluId等加密使用了这个加密
     * localSalesUpload用这个公钥加密
     */
    // 服务端公钥，放在客户端
    public static final String PUBLIC_KEY = "<RSAKeyValue><Modulus>rQZa25l8QY6TqsFGhnd+J1ZkDqNj7bEFhxh+jQRSU+U5SZvmMoG8htW+jF/phySJ5xdzyycdPU6JruagfQ+jBLVWWi05dq8uq62+BzP20DJUznZO/hpNYPDi+moxA9SFf2Mc6prr+fqUAof4v8o0NEHM7hWsLdSU34AGMiutKzM=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";
    // 服务端私钥，放在服务端
    public static final String PRIVATE_KEY_CMP = "<RSAKeyValue><Modulus>rQZa25l8QY6TqsFGhnd+J1ZkDqNj7bEFhxh+jQRSU+U5SZvmMoG8htW+jF/phySJ5xdzyycdPU6JruagfQ+jBLVWWi05dq8uq62+BzP20DJUznZO/hpNYPDi+moxA9SFf2Mc6prr+fqUAof4v8o0NEHM7hWsLdSU34AGMiutKzM=</Modulus><Exponent>AQAB</Exponent><P>tmKUYOh62Ub49hRCpBffMrFaWAXsLk9MkcAY+jyE6jFbtV3M4XEjM0Kk5D87iKVnpD0UmUeOuuLOOwdaDpbltw==</P><Q>8tyaOcvZVCHftj0eNtiNcHJWaP37QLlIqGvYmnnuiVU4MPRJ9XaiHQOXsAb3oJe7YN6zoWwCM3eQWYs9t0/GZQ==</Q><DP>S1NdisSZcEFis2dcup7tXOdos7AGgn3X1t5qsnbqQjsOcFKEOpvaeOJObcI72UzPCEL9zeyC42am3DRGGn5x8w==</DP><DQ>LgGqWa6LMgvktEIpuhwV1yphwivy79TptbYQZ1sX2/4/0c5FQHHDzhlDH/6B5HM59OTbRIYQeK16s+vTuikrnQ==</DQ><InverseQ>Ri5UtNA5W6CPXiFaeCLRlD1k8VyfJeoB3CncnA+ei8UAQo/5rrOeuup2ihAAAetZARsu+E0Gz4MWxSkgZYzjhA==</InverseQ><D>Cv1M1wbMHBPUsDM/afn78r6rczyQc7/bFIYLP6SnH/n+pfy3ZbzUvxGjXSIYYIODGMDmE2CkHEk5OHkgd2D/XwmtDRDs0NDCQlEkV/pui/ND0Nm7b7CoGXI89NwuQS5P8qAF9+ZKyBeLAggk7oaoupkKvVdHlBuRWq/Zs1REg70=</D></RSAKeyValue>";
    /*************************************************************/


    /*************************************************************/
    // 客户端公钥
    public static final String PUBLIC_KEY_CMP2 = "<RSAKeyValue><Modulus>1A0sfo4a0ureiZIw90CPvFHFvKzSpJM4zbrQbhzsxTKbTI8o12hw3Pw/tIMdNdGJjXZKyIT5GVzXnz29aKnYoXVTSU6TWUw2/ep63vVi+Pyi8GiDjo/tJjLbUEY4VY5d+Nw6GjHwv0Ulx54JzVXEDZUlw0KueR7ECGOOAsEe00M=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";
    // 客户端私钥，这个放在客户端
    public static final String PRIVATE_KEY_LIANMENG = "<RSAKeyValue><Modulus>1A0sfo4a0ureiZIw90CPvFHFvKzSpJM4zbrQbhzsxTKbTI8o12hw3Pw/tIMdNdGJjXZKyIT5GVzXnz29aKnYoXVTSU6TWUw2/ep63vVi+Pyi8GiDjo/tJjLbUEY4VY5d+Nw6GjHwv0Ulx54JzVXEDZUlw0KueR7ECGOOAsEe00M=</Modulus><Exponent>AQAB</Exponent><P>9JaP5WJ2+kdRbdiG3/lVJh5UjlhaCxE/IRe7SFExzfGd3ghob1nhuTLeg+5nKNiKerfiqJsghd3EudfO4dl+0Q==</P><Q>3fH7wQ0mFKNPZVQTwiqvulzn3PVDOCRxci+Q6AqqCiIAisLpv8qH1Fw1B0wCEzvOBUWdpscxWpnMONUNEUu90w==</Q><DP>l0ld3jumbi6Y3oH1Ayq9EWPnV1is8XWMf9KnBBc84C7YQBMhTebhqOUfH6nh4zD1juyY5UN2vXasDWvASpB2UQ==</DP><DQ>tU9TXLgDHeTznVF7+qioedQfzg/GY/zReHiHJihgFHsGelUUY4tuPvs8jsN3BoNaoE/opDAhj2QIw3y4eiKn5Q==</DQ><InverseQ>HB5tk8yk8wCW62xbzfIRZQ5bFAHLl2pUiyyGcUWiL4RN524bjUIuWu7WYuvQEdbpA5pO6jew75NCZBXsfmciWw==</InverseQ><D>QrXARTIcgzUrfFkW+XoAngyVqhUukWLVcHdwgeusmx8gFzZEcXvkYckjm3zSEC/S18OapDmdChsYpHquX0JrqVDz+jVuxJX+M50U3M7Zk79VyDyFH1v0rVbeIGjmOw4blxgQhnDG6sJ+cTknsISaL9wvldxnnULumHbVBjmvZvE=</D></RSAKeyValue>";
    // 这个私钥跟上面的私钥是一样的
    public static final String PRIVATE_KEY_CMP2 = "<RSAKeyValue><Modulus>1A0sfo4a0ureiZIw90CPvFHFvKzSpJM4zbrQbhzsxTKbTI8o12hw3Pw/tIMdNdGJjXZKyIT5GVzXnz29aKnYoXVTSU6TWUw2/ep63vVi+Pyi8GiDjo/tJjLbUEY4VY5d+Nw6GjHwv0Ulx54JzVXEDZUlw0KueR7ECGOOAsEe00M=</Modulus><Exponent>AQAB</Exponent><P>9JaP5WJ2+kdRbdiG3/lVJh5UjlhaCxE/IRe7SFExzfGd3ghob1nhuTLeg+5nKNiKerfiqJsghd3EudfO4dl+0Q==</P><Q>3fH7wQ0mFKNPZVQTwiqvulzn3PVDOCRxci+Q6AqqCiIAisLpv8qH1Fw1B0wCEzvOBUWdpscxWpnMONUNEUu90w==</Q><DP>l0ld3jumbi6Y3oH1Ayq9EWPnV1is8XWMf9KnBBc84C7YQBMhTebhqOUfH6nh4zD1juyY5UN2vXasDWvASpB2UQ==</DP><DQ>tU9TXLgDHeTznVF7+qioedQfzg/GY/zReHiHJihgFHsGelUUY4tuPvs8jsN3BoNaoE/opDAhj2QIw3y4eiKn5Q==</DQ><InverseQ>HB5tk8yk8wCW62xbzfIRZQ5bFAHLl2pUiyyGcUWiL4RN524bjUIuWu7WYuvQEdbpA5pO6jew75NCZBXsfmciWw==</InverseQ><D>QrXARTIcgzUrfFkW+XoAngyVqhUukWLVcHdwgeusmx8gFzZEcXvkYckjm3zSEC/S18OapDmdChsYpHquX0JrqVDz+jVuxJX+M50U3M7Zk79VyDyFH1v0rVbeIGjmOw4blxgQhnDG6sJ+cTknsISaL9wvldxnnULumHbVBjmvZvE=</D></RSAKeyValue>";

    /*************************************************************/

    /**************************CRM相关的密钥，已经完成配对 start***********************************/


    // CRM 服务端的公钥 ，callWebService用这个进行了加密
    public static final String PUBLIC_KEY_LIANMENG = "<RSAKeyValue><Modulus>b2kBrWhcWE5rSovNtXDn9bRTXe1atGxGaGGyC80ytseqTrai+GnHqtswypxrqh3Pgigv48qBJDV7g9ZjTaDvnTTgUFFA0GZbri8bxLJBv5XsvwnbJkd9FqYO/8+dUYJhbsPtFWSaXCMVNqFgcmdrxP7XaN4cupfVPCWxd7MYtv8=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";

    // CRM 客户端公钥
    public static final String CLIENT_PUBLIC_KEY_CRM = "<RSAKeyValue><Modulus>ULR6ekEXCMS1gCl9fy6v/K3+Xz0ubpaB/TKexkbFXbLEmtIWgM6E4IqOKqK11py2ANZrhcCJSFVlKpY8IG/KfhyIFsP7g8QTnWIh5R406H691kr80NZSs4n42EV9SQRG/z01b4MLsd0PVceGW1dCdnzedAnUesX8o4dE3qYtnFc=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";
    // CRM 服务端私钥
    public static final String SERVER_PRIVATE_KEY_CRM = "<RSAKeyValue><Modulus>b2kBrWhcWE5rSovNtXDn9bRTXe1atGxGaGGyC80ytseqTrai+GnHqtswypxrqh3Pgigv48qBJDV7g9ZjTaDvnTTgUFFA0GZbri8bxLJBv5XsvwnbJkd9FqYO/8+dUYJhbsPtFWSaXCMVNqFgcmdrxP7XaN4cupfVPCWxd7MYtv8=</Modulus><Exponent>AQAB</Exponent><P>r6H0B+2435M43Yj/bV4gMooS0yiJJKGziyYXdT6nJdRIS3gl6fmjFhvgOodeqSlQRYlXdd/achWu8D5wfneBRw==</P><Q>omPf6pZ/oVF5/UNZKIJdkXbgApayHOwyk1FfQvl7Erl4PXk98OOy9GfGc9UG+Q+Gq/ixYcl5Le880/ym/rs4iQ==</Q><DP>k3rzaBT+wP7nKV/43OXFM6st05KeVtxR3+7Ek/dgdCul6XminK67e708tOknW/UNKtcBUkH/NP1wQNytqgS/0Q==</DP><DQ>EV3/F5aq5bTSCB0IzwVKPoFpKb22JNO6APll58LIv9LoIiLyVe30OKFUnxS8Xyyi6Yd6aQvkEt6QMdfGgF3DiQ==</DQ><InverseQ>XtGJ7zJ7jhtKyXGh3H+e9IsnsMFvx5y2ndEPoBAHbLlboTVy0XIYHcyLubpTQq8R9HUK1gTnF4sFtjYnJe1Pkw==</InverseQ><D>NYnauoB6UB1vReWlN1pIvjSoE20qt6WIuESqkS1M/7U3zJfL/WCoCR1IdzVwL9IflEbHrMn2AXaKIrL2CaobO2hq4nWMX9pvsRu1BxkkS8Mbc4hfGjRB26ptUf4iK/VoekPyDEFKyxEncmuwOIrTjL5vlrPC5WqVeus7PcTo/yE=</D></RSAKeyValue>";
    // CRM 客户端私钥
    public static final String CLIENT_PRIVATE_KEY_CRM = "<RSAKeyValue><Modulus>ULR6ekEXCMS1gCl9fy6v/K3+Xz0ubpaB/TKexkbFXbLEmtIWgM6E4IqOKqK11py2ANZrhcCJSFVlKpY8IG/KfhyIFsP7g8QTnWIh5R406H691kr80NZSs4n42EV9SQRG/z01b4MLsd0PVceGW1dCdnzedAnUesX8o4dE3qYtnFc=</Modulus><Exponent>AQAB</Exponent><P>lB+jUZ4So+nyC27l+1H/MfddRbCoKsQWedoGdZq50bHtOJLOOTxMskJhsM/7nzEqbX00Nb/d2vgzD/GZiNONkw==</P><Q>i3s6Ec2RrlfefEJ5F3y6rZkUc6h7jkvrtrn+zt7XltSS7K39M2WKEX50hczyV45V42rJN2WqGUHeumB2VMNQrQ==</Q><DP>SSnTbrYIeQRONQ2C9X9L8O9BF/IpLm6Kt4dUl3RkgkpDZSVGTYkWTRpyzHX+0GPllA06462wtL7R4fcxIh90lw==</DP><DQ>NYWnlsVHLZtSKGRpivW1vH+NNYRlRQkk/g0JFeC04Y/YgTGSg3JnNKdNlzo5lBduJcIITFWam+6GxQXW4lfAUQ==</DQ><InverseQ>Ri387F5sa2TKvyLmFfsxHzygKSrx+ZWcEXwqtGdyKo9IA8EWQYXAmRR1zyZNCiq9OnWfPkwoqbSnEqxiEUN8yg==</InverseQ><D>Kr0Ix0lE+33AaFiQfxOoWvWBacoqlqHZwM5XysSbrF0JRLvQ8vFq6MY3N1jnVvmyYIssca5ehRHD+2OH/91zq/D9Z489YaUS55seT74lpLbjAQknVnvSX7zpadx1rXwr2qJDCcpc0Ix8R43kL+oWMGtjn7il5S00Dp2zL9KGEbE=</D></RSAKeyValue>";
    // CRM服务端公钥
    public static final String SERVER_PUBLIC_KEY_CRM = "<RSAKeyValue><Modulus>b2kBrWhcWE5rSovNtXDn9bRTXe1atGxGaGGyC80ytseqTrai+GnHqtswypxrqh3Pgigv48qBJDV7g9ZjTaDvnTTgUFFA0GZbri8bxLJBv5XsvwnbJkd9FqYO/8+dUYJhbsPtFWSaXCMVNqFgcmdrxP7XaN4cupfVPCWxd7MYtv8=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";
    /**************************CRM相关的密钥 end***********************************/


    // 配置的加解密的工具对象
//    public static final RSA RSA_CMP = new RSA("<RSAKeyValue><Modulus>yxRBOGjrXlZQprBSHTwwitl9SoyEAtLm9Lnl8mws4XkJ9/6uKpkXZDuYM1u4hNOnGuRMPFXn2zksPc5KKIeH894i0JtGNyGFnpZCXQ1DefkD4XTATzXQL2osvP1UBy0aA8ZWY5brNgOetq4chfGoMXaH9ZHmZQsYmygbIklmFDc=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>", "<RSAKeyValue><Modulus>rQZa25l8QY6TqsFGhnd+J1ZkDqNj7bEFhxh+jQRSU+U5SZvmMoG8htW+jF/phySJ5xdzyycdPU6JruagfQ+jBLVWWi05dq8uq62+BzP20DJUznZO/hpNYPDi+moxA9SFf2Mc6prr+fqUAof4v8o0NEHM7hWsLdSU34AGMiutKzM=</Modulus><Exponent>AQAB</Exponent><P>tmKUYOh62Ub49hRCpBffMrFaWAXsLk9MkcAY+jyE6jFbtV3M4XEjM0Kk5D87iKVnpD0UmUeOuuLOOwdaDpbltw==</P><Q>8tyaOcvZVCHftj0eNtiNcHJWaP37QLlIqGvYmnnuiVU4MPRJ9XaiHQOXsAb3oJe7YN6zoWwCM3eQWYs9t0/GZQ==</Q><DP>S1NdisSZcEFis2dcup7tXOdos7AGgn3X1t5qsnbqQjsOcFKEOpvaeOJObcI72UzPCEL9zeyC42am3DRGGn5x8w==</DP><DQ>LgGqWa6LMgvktEIpuhwV1yphwivy79TptbYQZ1sX2/4/0c5FQHHDzhlDH/6B5HM59OTbRIYQeK16s+vTuikrnQ==</DQ><InverseQ>Ri5UtNA5W6CPXiFaeCLRlD1k8VyfJeoB3CncnA+ei8UAQo/5rrOeuup2ihAAAetZARsu+E0Gz4MWxSkgZYzjhA==</InverseQ><D>Cv1M1wbMHBPUsDM/afn78r6rczyQc7/bFIYLP6SnH/n+pfy3ZbzUvxGjXSIYYIODGMDmE2CkHEk5OHkgd2D/XwmtDRDs0NDCQlEkV/pui/ND0Nm7b7CoGXI89NwuQS5P8qAF9+ZKyBeLAggk7oaoupkKvVdHlBuRWq/Zs1REg70=</D></RSAKeyValue>");
//    public static final RSA RSA_CMP2 = new RSA("<RSAKeyValue><Modulus>1A0sfo4a0ureiZIw90CPvFHFvKzSpJM4zbrQbhzsxTKbTI8o12hw3Pw/tIMdNdGJjXZKyIT5GVzXnz29aKnYoXVTSU6TWUw2/ep63vVi+Pyi8GiDjo/tJjLbUEY4VY5d+Nw6GjHwv0Ulx54JzVXEDZUlw0KueR7ECGOOAsEe00M=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>", "<RSAKeyValue><Modulus>1A0sfo4a0ureiZIw90CPvFHFvKzSpJM4zbrQbhzsxTKbTI8o12hw3Pw/tIMdNdGJjXZKyIT5GVzXnz29aKnYoXVTSU6TWUw2/ep63vVi+Pyi8GiDjo/tJjLbUEY4VY5d+Nw6GjHwv0Ulx54JzVXEDZUlw0KueR7ECGOOAsEe00M=</Modulus><Exponent>AQAB</Exponent><P>9JaP5WJ2+kdRbdiG3/lVJh5UjlhaCxE/IRe7SFExzfGd3ghob1nhuTLeg+5nKNiKerfiqJsghd3EudfO4dl+0Q==</P><Q>3fH7wQ0mFKNPZVQTwiqvulzn3PVDOCRxci+Q6AqqCiIAisLpv8qH1Fw1B0wCEzvOBUWdpscxWpnMONUNEUu90w==</Q><DP>l0ld3jumbi6Y3oH1Ayq9EWPnV1is8XWMf9KnBBc84C7YQBMhTebhqOUfH6nh4zD1juyY5UN2vXasDWvASpB2UQ==</DP><DQ>tU9TXLgDHeTznVF7+qioedQfzg/GY/zReHiHJihgFHsGelUUY4tuPvs8jsN3BoNaoE/opDAhj2QIw3y4eiKn5Q==</DQ><InverseQ>HB5tk8yk8wCW62xbzfIRZQ5bFAHLl2pUiyyGcUWiL4RN524bjUIuWu7WYuvQEdbpA5pO6jew75NCZBXsfmciWw==</InverseQ><D>QrXARTIcgzUrfFkW+XoAngyVqhUukWLVcHdwgeusmx8gFzZEcXvkYckjm3zSEC/S18OapDmdChsYpHquX0JrqVDz+jVuxJX+M50U3M7Zk79VyDyFH1v0rVbeIGjmOw4blxgQhnDG6sJ+cTknsISaL9wvldxnnULumHbVBjmvZvE=</D></RSAKeyValue>");
//    public static final RSA RSA_CRM = new RSA("<RSAKeyValue><Modulus>ULR6ekEXCMS1gCl9fy6v/K3+Xz0ubpaB/TKexkbFXbLEmtIWgM6E4IqOKqK11py2ANZrhcCJSFVlKpY8IG/KfhyIFsP7g8QTnWIh5R406H691kr80NZSs4n42EV9SQRG/z01b4MLsd0PVceGW1dCdnzedAnUesX8o4dE3qYtnFc=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>", "<RSAKeyValue><Modulus>b2kBrWhcWE5rSovNtXDn9bRTXe1atGxGaGGyC80ytseqTrai+GnHqtswypxrqh3Pgigv48qBJDV7g9ZjTaDvnTTgUFFA0GZbri8bxLJBv5XsvwnbJkd9FqYO/8+dUYJhbsPtFWSaXCMVNqFgcmdrxP7XaN4cupfVPCWxd7MYtv8=</Modulus><Exponent>AQAB</Exponent><P>r6H0B+2435M43Yj/bV4gMooS0yiJJKGziyYXdT6nJdRIS3gl6fmjFhvgOodeqSlQRYlXdd/achWu8D5wfneBRw==</P><Q>omPf6pZ/oVF5/UNZKIJdkXbgApayHOwyk1FfQvl7Erl4PXk98OOy9GfGc9UG+Q+Gq/ixYcl5Le880/ym/rs4iQ==</Q><DP>k3rzaBT+wP7nKV/43OXFM6st05KeVtxR3+7Ek/dgdCul6XminK67e708tOknW/UNKtcBUkH/NP1wQNytqgS/0Q==</DP><DQ>EV3/F5aq5bTSCB0IzwVKPoFpKb22JNO6APll58LIv9LoIiLyVe30OKFUnxS8Xyyi6Yd6aQvkEt6QMdfGgF3DiQ==</DQ><InverseQ>XtGJ7zJ7jhtKyXGh3H+e9IsnsMFvx5y2ndEPoBAHbLlboTVy0XIYHcyLubpTQq8R9HUK1gTnF4sFtjYnJe1Pkw==</InverseQ><D>NYnauoB6UB1vReWlN1pIvjSoE20qt6WIuESqkS1M/7U3zJfL/WCoCR1IdzVwL9IflEbHrMn2AXaKIrL2CaobO2hq4nWMX9pvsRu1BxkkS8Mbc4hfGjRB26ptUf4iK/VoekPyDEFKyxEncmuwOIrTjL5vlrPC5WqVeus7PcTo/yE=</D></RSAKeyValue>");
//

}
