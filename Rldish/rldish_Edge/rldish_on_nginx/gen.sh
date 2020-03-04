 #!/bin/bash
  
 for i in {3..9}
 do
   curl -I http://localhost/arbutus/content2K/2k10/0$i.ts
 done
 curl -I http://localhost/arbutus/content2K/2k10/10.ts
