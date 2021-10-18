**ARGUMENTS**

`-i, --input`

Must be followed by relative path to a .ppm input file.

`-o, --output`

Must be followed by the desired name of the output file. The name must end in .ppm, 
and the generated file will be placed in the current directory. 
    
`-x`

Applies a color filter. Must be followed by either "grayscale", "sepia", or 
"negative"; e.g. '-i input.ppm -x sepia'.

`-c, --compression`

Compresses the image to the specified threshold. Requires user to specify 
threshold in following argument; e.g. '-i input.ppm -c 400'. The threshold 
represents the variance required for a block of pixels to be compressed
into a single color. Basically, the higher the number, the more compressed the image will be.
    
`-e, --edge `

blacks out the image except for edges, which are colored white. Requires user to specify 
threshold in follow argument; e.g. '-i input.ppm -e 400'.

`-t, --outline`

Outlines which groups of pixels were compressed. Can only be used when compression is 
also used; e.g. '-i input.ppm -c 400 -t'.
    
    
