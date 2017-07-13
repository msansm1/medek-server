
angular.module('medekApp.controllers').controller('FriendController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'$modal',
'FriendService',
function($scope, $rootScope, $stateParams, $location, $modal, FriendService) {
    this.userLogin = $rootScope.user.login;
    
    if (typeof ($stateParams.friendId) != 'undefined') {
	    $scope.friend = [ FriendService.friend($stateParams.friendId)
	                               .then(
			                          function(response) {
			                        	  $('.mainlist').addClass('col-md-4');
			                        	  $('.mainlist').removeClass('col-md-8');
			                              console.log("Friend : "+response.data);
			                              $scope.friend = response.data;
			                              $('.itempanel').addClass('col-md-6');
			                          }, function(reason) {
			                              alert('FAILED !!!');
			                          }) ];
    } else {
    	$scope.friend = { 
    	};
    	$('.mainlist').addClass('col-md-4');
    	$('.mainlist').removeClass('col-md-8');
    	$('.itempanel').addClass('col-md-6');
	}

	// for date picker
	$scope.open = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.opened = true;
	};
    
} ]);
