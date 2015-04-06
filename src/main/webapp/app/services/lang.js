angular.module('medekApp.services').service('LangService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.langs = function() {
        return $http.get('services/langs');
    };
    
    this.lang = function(id) {
        return $http.get('services/langs/'+id);
    };
} ]);