package com.mpowloka.data.local.database.dao

import android.arch.persistence.room.Dao
import com.mpowloka.architecture.data.BaseDao
import com.mpowloka.data.local.database.entity.PersonIncidentLink

@Dao
abstract class PersonIncidentLinkDao : BaseDao<PersonIncidentLink>