
angular.module('medekApp.controllers').controller('FriendsController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'$modal',
'FriendService',
function($scope, $rootScope, $stateParams, $location, $modal, FriendService) {
    this.userLogin = $rootScope.user.login;
    $scope.filter = "";
    $scope.listLarge = true;
    
    $scope.getFriends = function() {
        $scope.listLarge = true;
		FriendService.friendslist(0, 50, 'id', 'asc').then(
    	           function(response) {
    	               console.log("Friends : "+response.data);
    	               $scope.friends = response.data;
    	           }, function(reason) {
    	               alert('FAILED !!!');
    	           });
    };
    
    $scope.friends = [ $scope.getFriends() ];
    
    $scope.openFriend = function(id) {
    };
    
    $scope.addFriend = function() {
    };
    
    $scope.searchFriends = function() {
        console.log('search criteria : '+$scope.filter);
    };

} ]);
