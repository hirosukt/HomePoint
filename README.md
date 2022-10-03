# HomePoint
Teleport to my home!

## Depends
* [CommandAPI](https://www.spigotmc.org/resources/api-commandapi-1-13-1-19-2.62353/)

## Commands

* `/home[h] {homeName}`  
teleport to my home. and you can teleport with vehicle(& passengers).  
If `homeName` is blank, you will teleport to "home".

* `/sethome[sh] <homeName>`  
set your location to home.

* `/delhome[dh] <homeName>`  
delete specify home.

* `/forcedelhome <homeName>`  
delete specify home through confirm.

* `/listhome[lh]`  
show your homes.

* `/loadhome`  
load config.

* `/savehome`  
save internal homes to config.

* `/renamehome[rh] <homeName> <after>`  
rename `homeName` home to `after`.

## Permissions

* `homepoint.home`  
use /home

* `homepoint.sethome`  
use /sethome

* `homepoint.delhome`  
use /delhome

* `homepoint.forcedelhome`  
use /forcedelhome

* `homepoint.listhome`  
use /listhome

* `homepoint.savehome`  
use /savehome

* `homepoint.loadhome`  
use /loadhome

* `homepoint.renamehome`  
use /renamehome
