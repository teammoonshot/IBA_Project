// 
// 
// 




void setup()
{
  Serial.begin(9600);

  int myArray[6];
  int array1[] = { 1,5,7,3,8 };
  int array2[6] = { 1,5,7,3,9 };
  char talk[6] = "hello";

  int undefined = myArray[0] + 2;
  Serial.println(undefined);

  int a = array1[0] + array1[1];
  Serial.println(a);

  Serial.println(array2[4]);
  array2[4] = 9;
  Serial.println(array2[4]);

  Serial.println(talk);
  talk[2] = 0x00;
  Serial.println(talk);
}

void loop()
{

}
