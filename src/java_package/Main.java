package java_package;
import java.util.BitSet;

public class Main {

    public static boolean jiffyCoef(boolean x, boolean y, boolean s){
        boolean res;
        return res = s & x ^ !s & y;
    }

    public static void main(String[] args) {
        String zvalues = "10110011001100100100101010010001010000000111100001100001101000011001110101101110001111101011111010010011111101011111110101100100011010100100011101111110100001100101001101011110110101010110100010001101001100100011100000011110110100011101000100111110001001111010011100000011100101011011101101011011111100111100100101100110111111011111111111111000011011010010100001011110110001011010110111011010001001111100001110100001010010000000011000111111110101010000001011000000001110010100111001110011101110011001010001110010100111110011010001110011001001111001001110000001000011011100001010011110011100110110101001010101100111010001100001001000101110001110110110001100101101101110100011010010111000100110011110101010011000111111100100011100000011101010011101101110001110000011100111100101001010110111001001110110000001000011010011111100010000000000110100101000001100000001001011100110111010100110100100010010000100100110011110011000010000110000110110010100111000111101000011010100101111100001001100001011101001111101100001100001100110010001111111011000000010001110101111110101010011011110110110100111010011010000011011001110100100111110000100011111010010100101100110000000100100000101101010000011101000100101011001001101000100010001010010011011000101011011101001011010011101001111010010101110011111001110100110110101100011110010010110111000101101111101010101100001000000110101101101100110111011110010000100011100000111111100110010101111010101001000110111101111111110011011111100011100111011111000011101101011000110010100001111101000100010011110010000001111011110111101101100011110010000111000000111001101101011110000111101000000111101101011111011000000000110011011101111011110101010110001100010110111100101001101000000010110001111001000101000110001100011110001000101000110011000111000101101100110101000001101110100110100111000111101101100001000010101111000110010011011011001011010011010010101000010110110000100101010101110110001011001000110010010110101101010101010110110111010101101011000011001010101010001010110101111101001010011000010001111001011110001001010";
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
                        l++;
                        if(l == 299){
                            System.out.println("L1: " + Integer.toBinaryString(registervalue1[j]));
                            System.out.println("L2: " + Integer.toBinaryString(registervalue2[m]));
                            System.out.println("L3: " + Integer.toBinaryString(i));
                        }
                    }while (jiffyCoef(linearShiftRegister1.registerStep(), linearShiftRegister2.registerStep(), linearShiftRegister3.registerStep()) == temp && l < 300);
                }


    }
}
