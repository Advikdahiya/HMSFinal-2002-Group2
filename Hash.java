public class Hash {

    public static String hashPassword(String password) {
        int[] primes = {31, 37, 41, 43, 47, 53, 59, 61, 67, 71};
        long hash = 7;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            int prime = primes[i % primes.length];
            hash = hash * prime + (c + i * prime);
            hash = hash ^ (hash >> 13); 
        }
        return Long.toHexString(hash);
    }

    public static boolean verifyPassword(String inputPassword, String storedHashedPassword) {
        String hashedInputPassword = hashPassword(inputPassword);
        return hashedInputPassword.equals(storedHashedPassword);
    }
}