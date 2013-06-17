YUI.add("yuidoc-meta", function(Y) {
   Y.YUIDoc = { meta: {
    "classes": [
        "Adapter",
        "ApplicationDescriptor",
        "Clause",
        "Database",
        "DatabaseDescriptor",
        "DatabaseMappingDescriptor",
        "DatabaseMappingDescriptor.Column",
        "DatabaseMappingDescriptor.Index",
        "DatabaseMappingDescriptor.Relationship",
        "Dictionary",
        "FunctionUtils",
        "HybridDescription.Adapter.Handler.Parameter",
        "HybridDescriptor.Adapter",
        "HybridDescriptor.Adapter.Handler",
        "HybridSiminovDatas",
        "HybridSiminovDatas.HybridSiminovData",
        "IAverage",
        "IAverageClause",
        "ICount",
        "ICountClause",
        "IDelete",
        "IDeleteClause",
        "IGroupConcat",
        "IGroupConcatClause",
        "IMax",
        "IMaxClause",
        "IMin",
        "IMinClause",
        "ISelect",
        "ISelectClause",
        "ISum",
        "ISumClause",
        "ITotal",
        "ITotalClause",
        "LibraryDescriptor",
        "Log",
        "SIDatasHelper",
        "Select",
        "Siminov",
        "SiminovException",
        "StringBuilder"
    ],
    "modules": [
        "Adapter",
        "Collection",
        "Database",
        "EventHandler",
        "Exception",
        "Impl"
    ],
    "allModules": [
        {
            "displayName": "Adapter",
            "name": "Adapter",
            "description": "It is one which describes properties required to map Web TO Native and vice-versa."
        },
        {
            "displayName": "Collection",
            "name": "Collection",
            "description": "A Collection represents a group of objects, know as its elements.\nSiminov Collection (SI Collection) is a set of classes and interfaces that implement commonly reusable collection data structures."
        },
        {
            "displayName": "Database",
            "name": "Database",
            "description": "Exposes classes which deal with database.\nA Siminov Database Abstraction Layer is an application programming interface which unifies the communication between a computer application and database such as SQLite.\nSiminov Database Layer reduce the amount of work by providing a consistent API to the developer and hide the database specifics behind this interface as much as possible."
        },
        {
            "displayName": "EventHandler",
            "name": "EventHandler",
            "description": "Any event triggered by Siminov is first handled by this function later it will deliver to appropriate Event APIs."
        },
        {
            "displayName": "Exception",
            "name": "Exception",
            "description": "It contain Siminov defined exceptions."
        },
        {
            "displayName": "Impl",
            "name": "Impl",
            "description": "Impl contain all interfaces required by database layer to deal with database."
        }
    ]
} };
});