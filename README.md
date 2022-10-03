# denglu
1第一次修改
没有加密，数据库直接存取密码，密码可能泄露，所以要使用哈希加盐法进行加密，这样只要盐值不同，做出的处理得到的加密密码也不同。
2.第二次修改
之前使用md5算法，但sha256比md5好，所以改成了sha256
@Component
public class SHA256Util {

    private static String strSalt;

    private static byte [] salt;

    @Value("${SHA.salt}")
    public void setStrSalt(String strSalt) {
        SHA256Util.strSalt = strSalt;
    }


    public static String getSecurePassword(String passwordToHash){

        salt = strSalt.getBytes();

        System.out.println("strSalt="+strSalt);
        System.out.println("salt="+salt);

        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Add password bytes to digest
            md.update(salt);
            // Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    
    第三次修改数据库层最好不要比较
    原因： java服务可以很简单的扩展 很多台。但是为了数据一致性  数据库扩展就很难，
    并且没有判断用户存不存在就进行加密，浪费了性能，所以在数据库只进行查询操作，获取数据
    之后在java层代码进行比较，有用户再进行加密和比较密码是否正确
    
    总结：刚开始感觉登录页面只需完成验证账号和密码是否正确就行，没想到最后加了许多自己以前不知道的东西，比如sha256算法，加盐，数据库层最好不要比较，而且springboot项目也是第一次
    接触，很多东西都不是很了解，很多地方花很长时间才看懂。

