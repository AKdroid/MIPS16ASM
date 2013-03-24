MIPS16ASM
=========

An assembler for MIPS-16 ISA version 1.0.

@author : Akhil Rao aka AkDroid.

This project aims in providing a basic assembler for MIPS-16 arrchitecture whose implementation in hardware is also 
done by us.

The assembler checks for proper format of all the possible 37 instructions and is majorly being used by us for debugging the hardware implementation.
Support for labels has not been provided yet and will be a subject of future versions.

The assembler can be launched by running the MIPS16ASM.jar file in dist folder of the source tree. A JRE should be installed in the system for do that. The application should be able to run in all OSes including Windows , Linux and MAC OS X although this hasn't been tested.

Assembler has the following features

1. Checks for the validity of assembly code.
2. Generates a <filename>.mmap file which contains a list of 65536 numbers separated by space in decimal format.
3. Generates a <filename>.asm file which contains the assembly code.
4. Generates a timestamped log file as <filename>.log which gives details of the last build.
5. Generates appropriate error and warning messages indicating the value og log.
6. Provides an interface to write and read current memory status of a single memory location by prompting the user to provide address and value wherver necessary
7. Allows the user to clear the code,save and open an existing .asm files at appropriate location.
8. Allows user to specify the start address.
9. Will load the previous saved code each time the assembler is run along with the memory map if it exists.

How-to-guides

1. Writing the code: Write the code in the big editor area provided. 
2. Writing a byte to a particular location: There are 2 methods for writing a byte to a specific address location. You can specify the address and value in the Memory editor Section to the right and click on the Write Button.Other way is to write the code in the format    
         address=value
3. Reading a byte:Reading a byte is similar to writing a byte. Provide the address in the appropriate field in the Memory Editor Section and Click on Read.This will provide the value stored at that location in decimal in the adjoining label tagged value.
4. The output file name and directory: Output file directories and filename can be specified in the Output file specification section. Type the appropriate value in the fields named Output file directory and Output filename (without extension).If the path does not exist the path will be created.
5. Assembling the file: Click on the assemble button located at the bottom of the editor. If the assembly was successful the memory map wiill be dumped in a <filename>.mmap file. This will also generate the <filename>.asm file and the build result will be stored in <filename>.log file. The build output is also visible in the output field.
6. Opening an existing file: Opening of an asm file is possible by clicking on the Open button,pointing it to the directory and choosing the required file. The changes done to the previous file after the last build will be discarded and the asssembly code will be loaded into the editor. Memory map if existing will also be updated. 
7. Clear Memory Map: Memory Map can be cleared to store zero by pressing the Clear Memory button.
8. Clear editor text: Clear the editor are by clicking on Clear button.

If you find a bug open an issue in github.


