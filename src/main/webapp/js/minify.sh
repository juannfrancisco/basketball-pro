##
## Juan Francisco Maldonado León (http://juannfrancisco.github.io)
## Copyright 2011-2016 Magno Labs, Inc.
##
##

uglifyjs /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/Application.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/services/GenericServices.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/MainController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/TeamController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/TeamProfileController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/TeamEditController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/TeamCreateController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/PlayerController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/PlayerCreateController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/PlayerEditController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/PlayerProfileController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/CourtController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/CourtCreateController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/CourtProfileController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/CourtEditController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/MatchController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/MatchPlayingController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/MatchProfileController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/MatchCreateController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/championship/ChampionshipController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/championship/ChampionshipEditController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/championship/ChampionshipProfileController.js \
         /Users/jmaldonado/Desarrollo/personal/magno/street-basket/basket/src/main/webapp/js/controladores/championship/ChampionshipCreateController.js \
         -o Application.min.js \
         --source-map Application.min.js.map \
        --source-map-root http://basket-magnolabs.rhcloud.com \
         -p 10 -c -m
         
#         --source-map-root http://localhost:9090/basket \
