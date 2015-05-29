
angular.module('medekApp.controllers').controller('ArtistsController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'ArtistService',
function($scope, $rootScope, $stateParams, $location, ArtistService) {
    this.userLogin = $rootScope.user.login;
    
    $scope.artists = [ ArtistService.artists()
                               .then(
		                          function(response) {
		                              console.log("Artists : "+response.data);
		                              $scope.artists = response.data;
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];
    
    $scope.openArtist = function(id) {
    	$location.path('/artists/'+id);
        $location.replace();
    };

} ]);