
angular.module('medekApp.controllers').controller('ArtistsController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'$modal',
'ArtistService',
function($scope, $rootScope, $stateParams, $location, $modal, ArtistService) {
    this.userLogin = $rootScope.user.login;
    $scope.filter = "";
    $scope.listLarge = true;
    
    $scope.getArtists = function() {
        $scope.listLarge = true;
    	ArtistService.artists().then(
           function(response) {
               console.log("Artists : "+response.data);
               $scope.artists = response.data;
           }, function(reason) {
               alert('FAILED !!!');
           });
    };
    
    $scope.artists = [ $scope.getArtists() ];
    
    $scope.openArtist = function(id) {
        $scope.listLarge = false;
    	$location.path('/artists/'+id);
        $location.replace();
    };
    
    $scope.createArtist = function() {
        var modalInstance = $modal.open({
            templateUrl: 'app/views/artists/addartist.html',
            controller: 'AddArtistController',
            scope: $scope
          });
    };
    
    $scope.searchArtists = function() {
        console.log('search criteria : '+$scope.filter);
    };

} ]);

angular.module('medekApp.controllers').controller('AddArtistController',[
'$scope',
'$rootScope',
'$modalInstance',
'ArtistService',
'ArtistTypeService',
function ($scope, $rootScope, $modalInstance, ArtistService, ArtistTypeService) {
	$scope.artist = { id: null,
			name: '',
			firstname: '',
			biolink: null,
			nationality: '',
			type: '',
			typeId: null
	};
	
    $scope.types = [ ArtistTypeService.artisttypes()
                     .then(
                        function(response) {
                            console.log("Artists types : "+response.data);
                            $scope.types = response.data;
                        }, function(reason) {
                            alert('FAILED !!!');
                        }) ];
	
    $scope.saveArtist = function() {
    	ArtistService.saveArtist($scope.artist).then(
                function(response) {
                    $rootScope.alerts.push({type: 'success', msg: 'Artist saved !'});
                	$scope.$parent.getArtists();
                }, function(reason) {
                    $rootScope.alerts.push({type: 'danger', msg: 'Save artist failed'});
                });
    };
	
    $scope.ok = function () {
    	$scope.saveArtist();
        $modalInstance.close('success');
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }]);