angular.module('medekApp.services').service('FriendService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.friends = function(id) {
        return $http.get('services/friends/'+id);
    };
    
    this.friend = function(userId, friendId) {
        return $http.get('services/friends/'+userId+'/'+friendId);
    };
    
    this.createupdate = function(userId, friendId, friend) {
        return $http.post('services/friends/'+userId+'/'+friendId, friend);
    };
    
    this.remove = function(userId, friendId) {
        return $http.get('services/friends/'+userId+'/'+friendId+'/delete');
    };
    
} ]);