
angular.module('medekApp.controllers').controller('ListsController',[
'$scope',
'$rootScope',
'$modal',
'CollectionService',
'ArtistTypeService',
'BookTypeService',
'EditorService',
'GenreService',
'LangService',
'StoryGenreService',
'SupportService',
function($scope, $rootScope, $modal, CollectionService, ArtistTypeService, BookTypeService,
		EditorService, GenreService, LangService, StoryGenreService, SupportService) {
    this.userLogin = $rootScope.user.login;
    $scope.lists = [];
    
    var artisttypes = {name : "artisttypes", iscollapsed: true, items : [] };
    ArtistTypeService.artisttypes().then(
    	       function(response) {
    	           console.log("Artisttypes : "+response.data);
    	           artisttypes.items = response.data;
    	       }, function(reason) {
    	           alert('FAILED !!!');
    	       });
    $scope.lists.push(artisttypes);
    
    var booktypes = {name : "booktypes", iscollapsed: true, items : [] };
    BookTypeService.booktypes().then(
    	       function(response) {
    	           console.log("Booktypes : "+response.data);
    	           booktypes.items = response.data;
    	       }, function(reason) {
    	           alert('FAILED !!!');
    	       });
    $scope.lists.push(booktypes);
    
    var collections = {name : "collections", iscollapsed: true, items : []};
    CollectionService.collections().then(
       function(response) {
           console.log("Collections : "+response.data);
           collections.items = response.data;
       }, function(reason) {
           alert('FAILED !!!');
       });
    $scope.lists.push(collections);
    
    var editors = {name : "editors", iscollapsed: true, items : []};
    EditorService.editors().then(
    	       function(response) {
    	           console.log("Editors : "+response.data);
    	           editors.items = response.data;
    	       }, function(reason) {
    	           alert('FAILED !!!');
    	       });
    $scope.lists.push(editors);
    
    var genres = {name : "genres", iscollapsed: true, items : []};
    GenreService.genres().then(
 	       function(response) {
 	           console.log("Genres : "+response.data);
 	           genres.items = response.data;
 	       }, function(reason) {
 	           alert('FAILED !!!');
 	       });
    $scope.lists.push(genres);
    
    var langages = {name : "langages", iscollapsed: true, items : []};
    LangService.langs().then(
  	       function(response) {
  	           console.log("Langages : "+response.data);
  	           langages.items = response.data;
  	       }, function(reason) {
  	           alert('FAILED !!!');
  	       });
    $scope.lists.push(langages);
    
    var supports = {name : "supports", iscollapsed: true, items : []};
    SupportService.supports().then(
  	       function(response) {
  	           console.log("Supports : "+response.data);
  	           supports.items = response.data;
  	       }, function(reason) {
  	           alert('FAILED !!!');
  	       });
     $scope.lists.push(supports);
    
    var storygenres = {name : "storygenres", iscollapsed: true, items : []};
    StoryGenreService.storygenres().then(
  	       function(response) {
  	           console.log("StoryGenres : "+response.data);
  	           storygenres.items = response.data;
  	       }, function(reason) {
  	           alert('FAILED !!!');
  	       });
     $scope.lists.push(storygenres);

     
     $scope.editList = function(listname, list) {
         var modalInstance = $modal.open({
             templateUrl: 'app/views/admin/list/editlist.html',
             controller: 'ListModalController',
             resolve: {
            	 list: function () {
                     return list;
                 },
            	 listname: function () {
            		 return listname;
            	 }
             }
           });
     };

} ]);

angular.module('medekApp.controllers').controller('ListModalController',[
'$scope',
'$rootScope',
'$modalInstance',
'CollectionService',
'ArtistTypeService',
'BookTypeService',
'EditorService',
'GenreService',
'LangService',
'StoryGenreService',
'SupportService',
'list',
'listname',
function ($scope, $rootScope, $modalInstance, CollectionService, ArtistTypeService, BookTypeService,
		EditorService, GenreService, LangService, StoryGenreService, SupportService, list, listname) {
	$scope.list = { name: listname,
			items: list
	};
    $scope.newitem = '';
	
	$scope.removeItem = function(item) {
		for (var i=0; i < $scope.list.items.length; i++) {
    		if($scope.list.items[i].name === item.name) {
    			if (item.id === null) {
    				$scope.list.items.splice(i, 1);
                } else {
                	$scope.list.items[i].name = 'deleted';
                }
                return;
    		}
		}
	};
    
    $scope.addItem = function() {
        $scope.list.items.push({ id: null,
                name: $scope.newitem
        });
        $scope.newitem = '';
    };

    $scope.ok = function () {
    	if (listname === 'collections') {
    		for (var i=0; i < $scope.list.items.length; i++) {
        		CollectionService.createUpdate($scope.list.items[i]);
    		}
    	}
    	if (listname === 'booktypes') {
    		for (var i=0; i < $scope.list.items.length; i++) {
    			BookTypeService.createUpdate($scope.list.items[i]);
    		}
    	}
    	if (listname === 'editors') {
    		for (var i=0; i < $scope.list.items.length; i++) {
    			EditorService.createUpdate($scope.list.items[i]);
    		}
    	}
    	if (listname === 'genres') {
    		for (var i=0; i < $scope.list.items.length; i++) {
    			GenreService.createUpdate($scope.list.items[i]);
    		}
    	}
    	if (listname === 'langages') {
    		for (var i=0; i < $scope.list.items.length; i++) {
    			LangService.createUpdate($scope.list.items[i]);
    		}
    	}
    	if (listname === 'supports') {
    		for (var i=0; i < $scope.list.items.length; i++) {
    			SupportService.createUpdate($scope.list.items[i]);
    		}
    	}
    	if (listname === 'storygenres') {
    		for (var i=0; i < $scope.list.items.length; i++) {
    			StoryGenreService.createUpdate($scope.list.items[i]);
    		}
    	}
        $modalInstance.close('success');
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }]);