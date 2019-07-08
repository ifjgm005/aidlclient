// ITreePlantCenter.aidl
package com.example.tuoanlan.servicebestdemo;

// Declare any non-default types here with import statements
import com.example.tuoanlan.servicebestdemo.ITree;
interface ITreePlantCenter {
   List<ITree> trees();
   int getTreesCount();
   void plantTreeInout( inout ITree tree);
   void plantTreeout( out ITree tree);
   void plantTreeIn( in ITree tree);
}
