package java_package;
import java.util.BitSet;

public class Main {

    public static boolean jiffyCoef(boolean x, boolean y, boolean s){
        boolean res;
        return res = s & x ^ !s & y;
    }

    public static void main(String[] args) {
        String zvalues = "00100010101010001100110011101001110100101110101110010010001010101110010111010111001011100001001010100101101111100010001001000101001001101011111010111010111011010010110000000101011100100110010001011000000110001011101010010011011101101011000111000010000011110000110110100010001101111011000000001101010100101000000011101111101111111110110100100000011110011110111111011101100000010101101011100010010001111100011100100010010101011111100110011110110101011000001110101101010011100100011111110001100111100101110100110111101011101100000100101100110111010101110010001000000111011001001011010101110110111101110000101011111111001101101010101010001000000110110011010110101101110010011010111110001011110110000001111110011100111101001010111010110101011111001111001000001010011110111001000000101110110110110000011000111101110000111011011111111101101011110010010111101011100000101001000100110101100010101110011110000100110000101001000011010001101101001000001101111100101000100110101100110111010101011001010000101000111110111111011101011010100111100000010110010100110010111010101110011010101100011101111000111100010111001001001110111111101010011111010100110011011011001110111110010100001001001110101111100100000110011110101000001101001011001100101110000110100110101011011000000101100001110101000011101001000000111110000111101010010111010101111111100011101111010101010100111010011000101011001101000001011100111011110010100101010110110011011010110001011100100010110000001011001111110010000110110111001011101001101011110000100000111010011100111110001100010101110110100110100100000010100000100101100010110100000010010111011111010111011100010000100011111101101100001011101100001100000111111011111101000010010000011001101111010001100111111011011000101011001000110000111110101101100100111000101110111010100001000110100100110010111001010101000000011110101111011010101001001001100100110001110010000010010000011101110001100100011110011010110011100111110101011011001101010110010011000011110111011000111010110111100100110101110100001100101011111011101010010010000111010011001110";
        LinearShiftRegister linearShiftRegister1 = new LinearShiftRegister(9, 25);
        LinearShiftRegister linearShiftRegister2 = new LinearShiftRegister(71, 26);
        LinearShiftRegister linearShiftRegister3 = new LinearShiftRegister(39, 27);
        BitSet regvalues = new BitSet();
        BitSet zbitvalues1 = new BitSet();
        BitSet zbitvalues2 = new BitSet();
        int[] registervalue1 = new int[10000];
        int[] registervalue2 = new int[10000];
        int N1 = (int) Math.pow( ((2.3263 * Math.pow( 0.25 * 0.75 , 0.5)) + (5.42 * Math.pow( 0.5 * 0.5, 0.5)))/0.25, 2);
        double C1 =  N1 * 0.25 + 2.3263 * Math.pow( N1 * 0.25 * 0.75, 0.5);
        int N2 = (int) Math.pow( ((2.3263 * Math.pow( 0.25 * 0.75 , 0.5)) + (5.54 * Math.pow( 0.5 * 0.5, 0.5)))/0.25, 2);
        double C2 =  N2 * 0.25 + 2.3263 * Math.pow( N2 * 0.25 * 0.75, 0.5);
        for(int i = 0; i < N1; i++){
            if(zvalues.charAt(i) == '1')
                zbitvalues1.set(i);
        }
        for(int i = 0; i < N2; i++){
            if(zvalues.charAt(i) == '1')
                zbitvalues2.set(i);
        }

        System.out.println("L1...");
        int k = -1;
        for(int i = 1; i < 33554432; i++){
            linearShiftRegister1.setRegisterValues(i);
            for(int j = 0; j < N1; j++){
                if(linearShiftRegister1.registerStep())
                    regvalues.set(j);
            }
            regvalues.xor(zbitvalues1);
            if(regvalues.cardinality() <= (int) C1 - 10) {
                k++;
                registervalue1[k] = i;
            }
            regvalues.clear();
        }

        System.out.println("L2...");
        int kk = -1;
        for(int i = 1; i < 67108864; i++){
            linearShiftRegister2.setRegisterValues(i);
            for(int j = 0; j < N2; j++){
                if(linearShiftRegister2.registerStep())
                regvalues.set(j);
            }
            regvalues.xor(zbitvalues2);
            if(regvalues.cardinality() <= (int) C2 - 10) {
                kk++;
                registervalue2[kk] = i;
            }
            regvalues.clear();
        }

        System.out.println("L3...");
        for(int i = 134217727; i >= 0; i--)
            for(int j = 0; j <= k; j ++)
                for(int m = 0; m <= kk; m++) {
                    linearShiftRegister1.setRegisterValues(registervalue1[j]);
                    linearShiftRegister2.setRegisterValues(registervalue2[m]);
                    linearShiftRegister3.setRegisterValues(i);
                    int l = 0;
                    boolean temp;
                    do{
                        if(zvalues.charAt(l) == '1')
                            temp = true;
                        else
                            temp = false;
                        if(l == 300){
                            System.out.println("L1: " + Integer.toBinaryString(registervalue1[j]));
                            System.out.println("L2: " + Integer.toBinaryString(registervalue2[m]));
                            System.out.println("L3: " + Integer.toBinaryString(i));
                        }
                        l++;
                    }while (jiffyCoef(linearShiftRegister1.registerStep(), linearShiftRegister2.registerStep(), linearShiftRegister3.registerStep()) == temp && l <= 300);
                }


    }
}
