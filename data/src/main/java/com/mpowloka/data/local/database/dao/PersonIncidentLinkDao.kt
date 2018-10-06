package com.mpowloka.data.local.database.dao

import androidx.room.Dao
import com.mpowloka.architecture.data.BaseDao
import com.mpowloka.data.local.database.entity.PersonIncidentLinksEntityRow

@Dao
abstract class PersonIncidentLinkDao : BaseDao<PersonIncidentLinksEntityRow>