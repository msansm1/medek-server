angular.module('medekApp.services').service('UserService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.updateProfile = function() {
        return $http.post('services/users/profile', $rootScope.user);
    };
    
    this.getAllUsers = function() {
        return $http.get('services/admin/users/');
    };
    
    this.getUser = function(id) {
        return $http.get('services/admin/users/'+id);
    };
    
    this.saveUser = function(user) {
        return $http.post('services/admin/users/', user);
    };
    
} ]);