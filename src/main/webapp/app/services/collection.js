angular.module('medekApp.services').service('CollectionService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.collections = function() {
        return $http.get('services/collections');
    };
    
    this.collection = function(id) {
        return $http.get('services/collections/'+id);
    };
} ]);