import java.util.Scanner;

public class VigenereCypher {

    /* cL=(tL+kL)%26
            text=SUN, key=BF; -> S(18)+B(1)=T(19)
        tL=cL-kL
        T(19)-B(1)=18
    * */
    private static char alphabet[]={'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N','O','P', 'Q', 'R','S', 'T', 'U','V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args){
        boolean con=true;
        while(con){

            Scanner sc = new Scanner(System.in);
            System.out.println("\n Hello! \nPlease choose 1 to encrypt. \n 2 to decrypt \n 3 to EXIT");
            String text;
            String key;
            int choice = sc.nextInt();
            if(choice==1){
                System.out.println("Enter text: ");
                text=sc.next();
                System.out.println("Enter the key: ");
                key=sc.next();
                encrypt(text, key);
            }else if(choice==2){
                System.out.println("Enter cyphertext: ");
                text=sc.next();
                System.out.println("Enter the key: ");
                key=sc.next();
                decrypt(text,key);
            }
            else{
                con=false;
            }

        }

    }

    public static void   encrypt(String text, String key /*, String initialVector*/){
        text=text.replaceAll("[^a-zA-Z]","");

        String keyMap=keySize(text,key);
        System.out.println(keyMap);

        text=text.toUpperCase();
        char pText[] = text.toCharArray();
        char pKey[]=keyMap.replaceAll("[^a-zA-Z]","").toUpperCase().toCharArray();

        int textSize=pText.length;
        int text_index[]=new int[textSize];
        int key_index[]= new int[textSize];


        //i am taking position numbers in alphabet[] of the text's letters
        for(int i=0; i<pText.length;i++){
            for(int j=0; j<alphabet.length;j++){
                if(pText[i]==alphabet[j]){
                    text_index[i]=j;
                }
            }
        }

        //i am taking position numbers in alphabet[] of the key's letters
        for(int i=0; i<pKey.length;i++){
            for(int j=0; j<alphabet.length;j++){
                if(pKey[i]==alphabet[j]){
                    key_index[i]=j;
                }
            }
        }
        char cText[]=new char[textSize];
        int res[]=new int[textSize];
        //сохраняю в массив res номер позиции зашифрованной буквы в массиве alphabet[]
        for(int i=0; i<textSize;i++){
             res[i]=(text_index[i]+key_index[i])%26;

        }
        //принтинг зашифрован буквы эккординг ту зе номер позиции в массиве alphabet[]
        for(int c=0;c<res.length;c++){
           System.out.print(alphabet[res[c]]);
        }

    }

    public static void decrypt(String cText, String key){

        char cypText[]=cText.replaceAll("[^a-zA-Z]","").toUpperCase().toCharArray();
        key=keySize(cText,key);
        char cKey[]=key.replaceAll("[^a-zA-Z]","").toUpperCase().toCharArray();

        int cText_idx[]=new int[cypText.length];
        int key_idx[]=new int[cypText.length];

        for(int i=0;i<cypText.length;i++){
            for(int j=0; j<alphabet.length;j++){
                if(cypText[i]==alphabet[j]){
                    cText_idx[i]=j;
                }
            }
        }

        for(int f=0; f<cKey.length; f++){
            for(int j=0; j<alphabet.length;j++){
                if(cKey[f]==alphabet[j]){
                    key_idx[f]=j;
                }
            }
        }

        int plaintext[]=new int[cypText.length];
        for(int i=0; i<cypText.length;i++){
             plaintext[i]=((cText_idx[i]-key_idx[i])%26);
                if(plaintext[i]<0){
                    plaintext[i]=plaintext[i]+26;
                }
            System.out.print(alphabet[plaintext[i]]);

        }



    }

    public static String keySize(String msg, String key){
        //making the key size grow to the size of message
        String keyMap="";
        for(int i=0,j=0; i<msg.length();i++){
            if(j<key.length()){
                keyMap+=key.charAt(j);
                j++;
            }else{
                //restarting the key from beginning after its length was complete
                j=0;
                keyMap+=key.charAt(j);
                j++;// j++ для того чтобы ключьтын биринши арипи не повторялось 2 раза
            }
        }
        return keyMap;
    }

}
