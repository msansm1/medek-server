angular.module('medekApp.services').service('FriendService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.friends = function(id) {
        return $http.get('services/friends/'+id);
    };

    this.friendslist = function(id, from, limit, orderBy, orderDir) {
        return $http.get('services/friends/'+id+'?from='+from+'&limit='+limit+'&orderBy='+orderBy+'&orderDir='+orderDir);
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