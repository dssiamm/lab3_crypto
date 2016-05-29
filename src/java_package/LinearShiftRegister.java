package java_package;


public class LinearShiftRegister {
    private int value, registercoeficientt, length;

    LinearShiftRegister(int registercoeficientt, int length) {
        this.length = length;
        this.registercoeficientt = registercoeficientt;
    }

    boolean registerStep(){
        boolean res;
        if( (this.value & 1) == 0)
            res = false;
        else
            res = true;
        int temp = this.value & this.registercoeficientt;
        this.value = this.value >>> 1;
        if(Integer.bitCount(temp) % 2 == 1)
            this.value = this.value + pow(2, this.length - 1);
        return res;
    }

    int pow(int x, int y){
        int res = x;
        for(int i = 0; i < y; i++)
            res *= x;
        return res;
    }

    void setRegisterValues(int values){
        this.value = values;
    }

    void showRegValues(){
        System.out.println(Integer.toBinaryString(this.value));
    }
}
